import WebSocket from "ws";

class ConquestClient {
    private socket: WebSocket;

    constructor(private serverUrl: string) {
        this.socket = new WebSocket(serverUrl);

        this.socket.on("open", () => {
            console.log("Connected to WebSocket server");
        });

        this.socket.on("message", (data: WebSocket.Data) => {
            console.log("Received message:", data.toString());
        });

        this.socket.on("close", () => {
            console.log("Disconnected from WebSocket server");
        });

        this.socket.on("error", (error: Error) => {
            console.error("WebSocket error:", error.message);
        });
    }

    sendMessage(destination: string, message: string): void {
        const formattedMessage = JSON.stringify({ destination, body: message });
        this.socket.send(formattedMessage);
    }

    close(): void {
        this.socket.close();
    }
}

class ConquestSession {
    private client: ConquestClient;

    constructor(private serverUrl: string) {
        this.client = new ConquestClient(serverUrl);
    }

    sendMessage(destination: string, message: string): void {
        this.client.sendMessage(destination, message);
    }

    end(): void {
        this.client.close();
    }

    chooseName(displayName: string): void {
        const message = JSON.stringify({ type: "choose_name", displayName });
        this.sendMessage("/app/chooseName", message);
    }
}

// Example usage:
const session = new ConquestSession("ws://localhost:8080");
session.sendMessage("/app/someDestination", "Hello, server!");
session.end();
