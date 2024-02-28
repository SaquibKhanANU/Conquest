import * as ApiAction from "../redux/actions/actions.ts";

const SockJS = require("sockjs-client");
const Stomp = require("stompjs");

class ConquestClient {
    constructor(serverUrl) {
        this.stompClient = Stomp.over(new SockJS(serverUrl));
        this.stompClient.heartbeat.outgoing = 10000;
        this.stompClient.heartbeat.incoming = 10000;
        this.stompClient.debug = null;
    }

    connect = async () => {
        return new Promise((resolve, reject) => {
            this.stompClient.connect(
                {},
                () => {
                    resolve(new ConquestSession(this.stompClient)); // Resolve with the connected stompClient
                },
                (error) => {
                    reject(error);
                }
            );
        });
    };
}

class ConquestSession {
    constructor(stompClient) {
        this.stompClient = stompClient;
    }

    // UTIL FUNCTIONS

    async sendMessage(destination, message) {
        if (this.stompClient && this.stompClient.send) {
            this.stompClient.send(destination, {}, message);
        } else {
            console.log(
                "Stomp client is not properly initialized or does not have a send method."
            );
        }
    }

    async sendEmptyMessage(destination) {
        if (this.stompClient && this.stompClient.send) {
            this.stompClient.send(destination);
        } else {
            console.log(
                "Stomp client is not properly initialized or does not have a send method."
            );
        }
    }

    async disconnect() {
        if (this.stompClient && this.stompClient.disconnect) {
            await this.stompClient.disconnect();
        } else {
            console.log(
                "Stomp client is not properly initialized or does not have a disconnect method."
            );
        }
    }

    async subscribe(dispatch, navigate) {
        const subscriptions = {}; // Object to store subscription IDs

        const handlePlayersMessage = (message) => {
            const players = JSON.parse(message.body);
            dispatch(ApiAction.setPlayers(players));
        };

        const handleLobbiesMessage = (message) => {
            const lobbies = JSON.parse(message.body);
            dispatch(ApiAction.setLobbies(lobbies));
        };

        const handleGamesMessage = (message) => {
            const games = JSON.parse(message.body);
            dispatch(ApiAction.setGames(games));
        };

        const handleCurrentPlayerMessage = (message) => {
            const player = JSON.parse(message.body);
            console.log("Received current player: ", player);
            dispatch(ApiAction.setCurrentPlayer(player));
            navigate("/gameBrowser");
        };

        const handleCurrentLobbyMessage = async (message) => {
            const lobby = JSON.parse(message.body);
            dispatch(ApiAction.setCurrentLobby(lobby));
            if (subscriptions.lobby) {
                subscriptions.lobby.unsubscribe();
                subscriptions.lobby = undefined;
            }
            if (
                lobby.lobbyId === undefined &&
                subscriptions.game === undefined
            ) {
                navigate("/gameBrowser");
            } else if (subscriptions.game === undefined) {
                navigate("/gameLobby/" + lobby.lobbyId);
                subscriptions.lobby = this.stompClient.subscribe(
                    `/topic/lobby/${lobby.lobbyId}`,
                    (message) => {
                        const updatedLobby = JSON.parse(message.body);
                        console.log("Received updated lobby: ", updatedLobby);
                        dispatch(ApiAction.setCurrentLobby(updatedLobby));
                    }
                );
            }
        };

        const handleCurrentGameMessage = (message) => {
            const game = JSON.parse(message.body);
            console.log("Received current game: ", game);
            dispatch(ApiAction.setCurrentGame(game));
            if (subscriptions.game) {
                subscriptions.game.unsubscribe();
                subscriptions.game = undefined;
            }
            if (game.gameId === undefined) {
                navigate("/gameBrowser");
            } else {
                navigate("/game/" + game.gameId);
                subscriptions.game = this.stompClient.subscribe(
                    `/topic/game/${game.gameId}`,
                    (message) => {
                        const updatedGame = JSON.parse(message.body);
                        console.log("Received updated game: ", updatedGame);
                        dispatch(ApiAction.setCurrentGame(updatedGame));
                    }
                );
            }
        };

        // Subscribe to topics
        this.stompClient.subscribe("/topic/players", handlePlayersMessage);
        this.stompClient.subscribe("/topic/lobbies", handleLobbiesMessage);
        this.stompClient.subscribe("/topic/games", handleGamesMessage);
        this.stompClient.subscribe(
            "/user/queue/player/currentPlayer",
            handleCurrentPlayerMessage
        );
        this.stompClient.subscribe(
            "/user/queue/player/currentLobby",
            handleCurrentLobbyMessage
        );
        this.stompClient.subscribe(
            "/user/queue/player/currentGame",
            handleCurrentGameMessage
        );
    }

    // --- PLAYERS ---
    async getPlayersList() {
        this.sendEmptyMessage("/app/players/getPlayersList");
    }

    async createOrUpdatePlayer(playerName) {
        this.sendMessage("/app/players/createOrUpdatePlayer", playerName);
    }

    // --- GameBrowser ---
    async getLobbiesList() {
        this.sendEmptyMessage("/app/lobbies/getLobbiesList");
    }

    async getGamesList() {
        this.sendEmptyMessage("/app/lobbies/getGamesList");
    }

    async createLobby(gameDefinitionJson) {
        this.sendMessage(
            "/app/lobby/createLobby",
            JSON.stringify(gameDefinitionJson)
        );
    }

    async joinLobby(lobbyId) {
        this.sendMessage("/app/lobby/joinLobby", lobbyId);
    }

    // --- GameLobby ---

    async leaveLobby(lobbyId) {
        this.sendMessage("/app/lobby/leaveLobby", lobbyId);
    }

    async disbandLobby(lobbyId) {
        this.sendMessage("/app/lobby/disbandLobby", lobbyId);
    }

    async startGame(lobbyId) {
        this.sendMessage("/app/lobby/startGame", lobbyId);
    }

    async readyUp() {
        this.sendEmptyMessage("/app/lobby/readyUp");
    }

    async chooseCivilization(civilization) {
        this.sendMessage(
            "/app/lobby/chooseCivilization",
            JSON.stringify(civilization)
        );
    }

    async kickPlayer(playerId) {
        this.sendMessage("/app/lobby/kickPlayer", playerId);
    }
}

export default ConquestClient;
