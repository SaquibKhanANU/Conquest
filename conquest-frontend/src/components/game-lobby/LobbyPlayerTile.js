import "./LobbyPlayerTile.css";
import React, { useState } from "react";
import { useSession } from "../global/contexts/SessionContext";

const LobbyPlayerTile = ({ playerData, ready, civilization, isOwner }) => {
    const { playerName } = playerData;
    const { session } = useSession();
    const [isHovered, setIsHovered] = useState(false);

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
                <p>{playerName}</p>
                <p style={{ color: civilization.color }}>{civilization.name}</p>
                {ready ? (
                    <p className="ready">&#10003;</p>
                ) : (
                    <p className="not-ready">&#10005;</p>
                )}
                <div className="button-container">
                    {isOwner && isHovered && (
                        <button onClick={handleKickPlayer}>KICK</button>
                    )}
                </div>
            </div>
        </div>
    );
};

export default LobbyPlayerTile;
