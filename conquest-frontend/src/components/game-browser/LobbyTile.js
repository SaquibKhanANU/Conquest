import "./LobbyTile.css";
import React from "react";
import { useSession } from "../global/contexts/SessionContext";

const LobbyTile = ({ gameLobbyData }) => {
    const { session } = useSession();
    const { lobbyId, lobbyRules, lobbyPlayers } = gameLobbyData;
    const { lobbyName, map, mode, maxPlayers, privacy } = lobbyRules;
    const isPrivate = privacy ? "YES" : "NO";

    const handleJoinLobby = async () => {
        console.log("Joining lobby...", gameLobbyData);
        session.joinLobby(lobbyId);
    };

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
                    <p>
                        {lobbyPlayers.length}/{maxPlayers}
                    </p>
                </div>
                <div>
                    <p>{isPrivate}</p>
                </div>
                <div>
                    <button
                        className="join-game-button"
                        onClick={handleJoinLobby}
                    >
                       &#10148;
                    </button>
                </div>
            </div>
        </div>
    );
};

export default LobbyTile;
