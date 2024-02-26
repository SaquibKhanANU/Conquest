import "./LobbyActions.css";
import React from "react";
import { useSession } from "../global/contexts/SessionContext";
import { useSelector } from "react-redux";

const LobbyActions = ({ lobbyId, lobbyOwner, playersReady }) => {
    const { session } = useSession();
    const { playerId } = lobbyOwner;
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const isOwner = playerId === currentPlayer.playerId;
    const isReady = playersReady.includes(currentPlayer.playerId);

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
        // session.startGame(lobbyId);
    }

    return (
        <div className="actions-lobby-container">
            <div className="lobby-api-actions">
                {isOwner ? (
                    <button
                        className="lobby-action-button red-hover"
                        onClick={handleDisbandLobby}
                    >
                        DISBAND Lobby &#10005;
                    </button>
                ) : (
                    <button
                        className="lobby-action-button red-hover"
                        onClick={handleLeaveLobby}
                    >
                        Leave Lobby <span className="arrow-left">&#10005;</span>
                    </button>
                )}
            </div>
            <div className="lobby-api-actions">
                <button
                    className={`lobby-action-button ${
                        !isReady ? "green-hover" : "red-hover"
                    }`}
                    onClick={handleReadyUp}
                >
                    {!isReady ? (
                        <>
                            Ready <span>&#10003;</span>
                        </>
                    ) : (
                        <>
                            Unready <span>&#10005;</span>
                        </>
                    )}
                </button>
                {isOwner && (
                    <button className="lobby-action-button green-hover">
                        Start Game &#10148;
                    </button>
                )}
            </div>
        </div>
    );
};

export default LobbyActions;
