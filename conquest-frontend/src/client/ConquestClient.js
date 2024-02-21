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

    sendMessage(destination, message) {
        if (this.stompClient && this.stompClient.send) {
            this.stompClient.send(destination, {}, message);
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

    async subscribe(dispatch) {
        this.stompClient.subscribe("/topic/players", (message) => {
            const players = JSON.parse(message.body);
            dispatch(ApiAction.setPlayers(players));
        });
        this.stompClient.subscribe("/topic/lobbies", (message) => {
            const lobbies = JSON.parse(message.body);
            dispatch(ApiAction.setLobbies(lobbies));
        });
        this.stompClient.subscribe(
            "/user/queue/player/currentPlayer",
            (message) => {
                const player = JSON.parse(message.body);
                dispatch(ApiAction.setCurrentPlayer(player));
            }
        );
        this.stompClient.subscribe(
            "/user/queue/player/currentLobby",
            (message) => {
                const lobby = JSON.parse(message.body);
                dispatch(ApiAction.setCurrentLobby(lobby));
            }
        );
    }

    // --- PLAYERS ---
    async getPlayersList() {
        this.sendMessage("/app/players/getPlayersList", "");
    }

    async createOrUpdatePlayer(playerName) {
        this.sendMessage("/app/players/createOrUpdatePlayer", playerName);
    }

    // --- GameBrowser ---
    async getLobbiesList() {
        this.sendMessage("/app/lobbies/getLobbiesList", "");
    }

    async createGame(gameDefinitionJson) {
        this.sendMessage(
            "/app/lobbies/createLobby",
            JSON.stringify(gameDefinitionJson)
        );
    }

    async joinLobby(lobbyId) {
        this.sendMessage("/app/lobbies/joinLobby", lobbyId);
    }

    async createLobbyAndJoin(gameDefinitionJson) {
        this.sendMessage(
            "/app/lobbies/createLobbyAndJoin",
            JSON.stringify(gameDefinitionJson)
        );
    
    }

    // --- GameLobby ---
    async getLobbyData(lobbyId) {
        this.sendMessage("/app/lobby/getLobbyData", lobbyId);
    }
}

export default ConquestClient;
