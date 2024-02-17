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
            console.log("Received message:", message.body);
            const players = JSON.parse(message.body);
            console.log("Received players:", players);
            dispatch(ApiAction.setPlayers(players));
        });
    }

    // PLAYERS

    async findAllPlayers() {
        this.sendMessage("/app/players/getPlayers", "");
    }

    // Function to call the findById endpoint
    async findPlayerById(id) {
        this.sendMessage(`/app/api/players/${id}`, "");
    }

    // Function to call the save endpoint
    async savePlayer(playerName) {
        console.log("Saving player:", playerName);
        this.sendMessage("/app/players/savePlayer", playerName);
    }

    // Function to call the deleteById endpoint
    async deletePlayerById(id) {
        this.stompClient.send(
            `/app/api/players/${id}`,
            { method: "DELETE" },
            ""
        );
    }
}

export default ConquestClient;
