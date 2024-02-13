const SockJS = require("sockjs-client");
const Stomp = require("stompjs");

class ConquestClient {
    constructor(serverUrl) {
        this.stompClient = Stomp.over(new SockJS(serverUrl));
        this.stompClient.heartbeat.outgoing = 10000;
        this.stompClient.heartbeat.incoming = 10000;
        this.stompClient.debug = null;
    }

    async connect() {
        return new Promise((resolve, reject) => {
            this.stompClient.connect(
                {},
                () => {
                    resolve(this.stompClient); // Resolve with the connected stompClient
                },
                (error) => {
                    reject(error);
                }
            );
        });
    }
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

    chooseName(name) {
        console.log("Choosing name:", name);
        this.sendMessage("/app/chooseName", name);
    }
}

// Testing
const client = new ConquestClient("http://localhost:8080/ws");
client
    .connect()
    .then((stompClient) => {
        // After connecting, create a ConquestSession with the connected stompClient
        const session = new ConquestSession(stompClient);
        // Use the session to send messages
        session.chooseName("Alice");
    })
    .catch((error) => {
        console.log("Error connecting:", error);
    });
