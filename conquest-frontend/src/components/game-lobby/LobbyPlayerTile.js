import "./LobbyPlayerTile.css";
import React from "react";

const LobbyPlayerTile = ({ playerData, ready, civilization }) => {
    const { playerName } = playerData;
    return (
        <div className="lobby-player-tile-container">
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
            </div>
        </div>
    );
};

export default LobbyPlayerTile;
