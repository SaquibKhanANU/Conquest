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
    const currentLobby = useSelector(
        (state) => state.currentPlayer.currentLobby
    );
    const { session } = useSession();
    const navigate = useNavigate();
    const [playersProfile, setPlayerProfile] = useState(currentPlayer);

    useEffect(() => {
        if (session === null) {
            navigate("/");
        }
    }, [navigate, session]);

    const handlePlayerClick = (player) => {
        setPlayerProfile(player);
    };

    return (
        <div
            className="game-lobby-container"
            style={{ backgroundImage: `url(${BackgroundImage})` }}
        >
            {currentLobby.lobbyId !== undefined && (
                <div className="game-lobby-body">
                    <div className="lobby-settings">
                        <LobbySettings
                            currentLobby={currentLobby}
                            currentPlayer={currentPlayer}
                        />
                    </div>
                    <div className="lobby-body">
                        <LobbyPlayersList
                            currentLobby={currentLobby}
                            currentPlayer={currentPlayer}
                            setPlayer={handlePlayerClick}
                        />
                        <LobbyActions
                            currentLobby={currentLobby}
                            currentPlayer={currentPlayer}
                        />
                    </div>
                    <div className="lobby-players">
                        <Profile player={playersProfile} />
                        <PlayersOnline />
                    </div>
                </div>
            )}
        </div>
    );
};

export default GameLobby;
