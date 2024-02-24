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
    const currentLobby = useSelector(
        (state) => state.currentPlayer.currentLobby
    );

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
                            lobbyRules={currentLobby.lobbyRules}
                            lobbyOwner={currentLobby.lobbyOwner}
                            lobbyPlayersLength={currentLobby.lobbyPlayers.length}
                            civilization={currentLobby.playerCivilizations[currentPlayer.playerId]}
                        />
                    </div>
                    <div className="lobby-body">
                        <div>
                            <LobbyPlayersList
                                players={currentLobby.lobbyPlayers}
                                playersReady = {currentLobby.playersReady}
                                playerCivilizations={currentLobby.playerCivilizations}
                                setPlayer={handlePlayerClick}
                            />
                        </div>
                        <div>
                            <LobbyActions
                                lobbyId={currentLobby.lobbyId}
                                lobbyOwner={currentLobby.lobbyOwner}
                                playersReady={currentLobby.playersReady}
                            />
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
            )}
            ;
        </div>
    );
};

export default GameLobby;
