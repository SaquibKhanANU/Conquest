import "./GameLobby.css";
import React, { useState, useEffect } from "react";
import BackgroundImage from "../../resources/imgs/game_browser_background_img.png";
import Profile from "../global/Profile";
import PlayersOnline from "../global/PlayersOnline";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useSession } from "../contexts/SessionContext";
import LobbyPlayersList from "./LobbyPlayersList";
import LobbyActions from "./LobbyActions";
import LobbySettings from "./LobbySettings";

const GameLobby = ({ lobbyData }) => {
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const navigate = useNavigate();
    const { session } = useSession();
    const [player, setPlayer] = useState(currentPlayer);

    useEffect(() => {
        if (session === null) {
            // navigate("/");
        }
    }, [navigate, session]);

    return (
        <div
            className="game-lobby-container"
            style={{ backgroundImage: `url(${BackgroundImage})` }}
        >
            <div className="game-lobby-body">
                <div className="lobby-settings">
                    <LobbySettings />
                </div>
                <div className="lobby-body">
                    <div>
                        <LobbyPlayersList />
                    </div>
                    <div>
                        <LobbyActions />
                    </div>
                </div>
                <div className="lobby-players">
                    <div>
                        <Profile player={player} />
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
