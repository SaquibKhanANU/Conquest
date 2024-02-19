import "./LobbyTile.css";
import React from "react";

const LobbyTile = ({ gameLobbyData }) => {
    const { lobbyRules } = gameLobbyData;
    const { lobbyName, map, mode, maxPlayers, privacy } = lobbyRules;
    const isPrivate = privacy ? "YES" : "NO";

    return (
        <div className="lobby-tile-container">
            <div className="lobby-tile-body">
                <div>
                    <p>{lobbyName}</p>
                </div>
                <div>
                    <p>{map}</p>
                </div>
                <div>
                    <p>{mode}</p>
                </div>
                <div>
                    <p>1/{maxPlayers}</p>
                </div>
                <div>
                    <p>{isPrivate}</p>
                </div>
                <div>
                    <button>JOIN</button>
                </div>
            </div>
        </div>
    );
};

export default LobbyTile;
