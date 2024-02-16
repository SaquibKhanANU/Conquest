import * as ApiAction from "../actions/actions.ts";

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

    async addPlayer(name) {
        this.sendMessage("/app/addPlayer", name);
    }

    async getPlayers(dispatch) {
        this.sendMessage("/app/getPlayers", "");
        this.stompClient.subscribe("/topic/players", (response) => {
            try {
                const players = JSON.parse(response.body);
                dispatch(ApiAction.setPlayers(players));
            } catch (error) {
                console.error("Error parsing players data:", error);
            }
        });
    }

    async createLobby(lobbyData) {
        this.sendMessage("/app/createLobby", JSON.stringify(lobbyData));
    }

    async getLobbies(dispatch) {
        this.sendMessage("/app/getLobbies", "");
        this.stompClient.subscribe("/topic/lobbies", (response) => {
            try {
                const lobbies = JSON.parse(response.body);
                dispatch(ApiAction.setLobbies(lobbies));
            } catch (error) {
                console.error("Error parsing lobbies data:", error);
            }
        });
    }
}

export default ConquestClient;
