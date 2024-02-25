import "./LobbyPlayerTile.css";
import React, { useState } from "react";
import { useSelector } from "react-redux";
import { useSession } from "../global/contexts/SessionContext";

const LobbyPlayerTile = ({ playerData, ready, civilization }) => {
    const { playerName } = playerData;
    const { session } = useSession();
    const [isHovered, setIsHovered] = useState(false);
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const currentLobby = useSelector(
        (state) => state.currentPlayer.currentLobby
    );
    const isOwner = currentLobby.lobbyOwner.playerId === currentPlayer.playerId;

    const handleKickPlayer = () => {
        console.log("Kicking player...");
        session.kickPlayer(playerData.playerId);
    };

    return (
        <div
            className="lobby-player-tile-container"
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
            <div className="lobby-player-tile-body">
                <div>
                    <p>{playerName}</p>
                </div>
                <div>
                    <p style={{ color: civilization.color }}>
                        {civilization.name}
                    </p>
                </div>
                <div>
                    {ready ? (
                        <p className="ready">&#10003;</p>
                    ) : (
                        <p className="not-ready">&#10005;</p>
                    )}
                </div>
                {isOwner && (
                    <div className="button-container">
                        {isHovered && (
                            <button onClick={handleKickPlayer}>KICK</button>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
};

export default LobbyPlayerTile;
