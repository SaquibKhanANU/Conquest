import "./LobbyActions.css";
import React from "react";
import { useSession } from "../global/contexts/SessionContext";

const LobbyActions = ({ currentLobby, currentPlayer }) => {
    const { lobbyId, playersReady, lobbyOwner } = currentLobby;
    const { playerId } = currentPlayer;
    const { session } = useSession();
    const isOwner = lobbyOwner.playerId === playerId;
    const isReady = playersReady.includes(playerId);

    const handleLeaveLobby = async () => {
        console.log("Leaving game...");
        session.leaveLobby(lobbyId);
    };

    const handleDisbandLobby = async () => {
        console.log("Disbanding game...");
        session.disbandLobby(lobbyId);
    };

    const handleReadyUp = async () => {
        console.log("Ready up...");
        session.readyUp(lobbyId);
    };

    const handleStartGame = async () => {
        session.startGame(lobbyId);
    }

    return (
        <div className="actions-lobby-container">
            <div className="lobby-api-actions">
                <button
                    className={`lobby-action-button red-hover`}
                    onClick={isOwner ? handleDisbandLobby : handleLeaveLobby}
                >
                    {isOwner ? "DISBAND Lobby" : "Leave Lobby"}{" "}
                    <span>&#10005;</span>
                </button>
            </div>
            <div className="lobby-api-actions">
                <button
                    className={`lobby-action-button ${
                        isReady ? "red-hover" : "green-hover"
                    }`}
                    onClick={handleReadyUp}
                >
                    {isReady ? "Unready" : "Ready"}{" "}
                    <span>{isReady ? String.fromCharCode(10005) : "✓"}</span>
                </button>
                {isOwner && (
                    <button className="lobby-action-button green-hover" onClick={handleStartGame}>
                        Start Game &#10148;
                    </button>
                )}
            </div>
        </div>
    );
};

export default LobbyActions;
