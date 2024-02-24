import "./GameLobby.css";
import React, { useState, useEffect } from "react";
import BackgroundImage from "../../resources/imgs/game_browser_background_img.png";
import Profile from "../global/profile/Profile";
import PlayersOnline from "../global/playersOnline/PlayersOnline";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useSession } from "../global/contexts/SessionContext";
import LobbyPlayersList from "./LobbyPlayersList";
import LobbyActions from "./LobbyActions";
import LobbySettings from "./LobbySettings";

const GameLobby = () => {
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const navigate = useNavigate();
    const { session } = useSession();
    const [playersProfile, setPlayerProfile] = useState(currentPlayer);
    const currentLobby = useSelector((state) => state.currentPlayer.currentLobby);

    useEffect(() => {
        if (session === null) {
            navigate("/");
        } 
    }, [navigate, session]);

    return (
        <div
            className="game-lobby-container"
            style={{ backgroundImage: `url(${BackgroundImage})` }}
        >
            <div className="game-lobby-body">
                <div className="lobby-settings">
                    {currentLobby.lobbyRules && (
                        <LobbySettings settings={currentLobby.lobbyRules} owner={currentLobby.lobbyOwner} />
                    )}
                </div>
                <div className="lobby-body">
                    <div>
                        {currentLobby.lobbyPlayers && (
                            <LobbyPlayersList
                                players={currentLobby.lobbyPlayers}
                            />
                        )}
                    </div>
                    <div>
                        <LobbyActions lobbyId={currentLobby.lobbyId} />
                    </div>
                </div>
                <div className="lobby-players">
                    <div>
                        <Profile player={playersProfile} />
                    </div>
                    <div>
                        <PlayersOnline />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default GameLobby;
