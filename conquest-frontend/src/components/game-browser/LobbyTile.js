import "./LobbyTile.css";
import React from "react";
import { useSession } from "../global/contexts/SessionContext";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

const LobbyTile = ({ gameLobbyData }) => {
    const { session } = useSession();
    const { lobbyId, lobbyRules } = gameLobbyData;
    const { lobbyName, map, mode, maxPlayers, privacy } = lobbyRules;
    const isPrivate = privacy ? "YES" : "NO";
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleJoinLobby = async () => {
        console.log("Joining lobby...", gameLobbyData);
        session.joinLobby(lobbyId, dispatch);
        navigate(`/gameLobby/${lobbyId}`);
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
                    <p>1/{maxPlayers}</p>
                </div>
                <div>
                    <p>{isPrivate}</p>
                </div>
                <div>
                    <button onClick={handleJoinLobby}>JOIN</button>
                </div>
            </div>
        </div>
    );
};

export default LobbyTile;
