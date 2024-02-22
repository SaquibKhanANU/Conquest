import "./GameBrowser.css";
import BackgroundImage from "../../resources/imgs/game_browser_background_img.png";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useSession } from "../global/contexts/SessionContext";
import CreateGame from "./CreateGame";
import JoinGame from "./JoinGame";
import Profile from "../global/profile/Profile";
import PlayersOnline from "../global/playersOnline/PlayersOnline";
import { useSelector } from "react-redux";

const GameBrowser = () => {
    const { session } = useSession();
    const navigate = useNavigate();
    const currentPlayer = useSelector((state) => state.currentPlayer.player);

    useEffect(() => {
        if (session === null) {
            navigate("/");
        } 
    }, [navigate, session]);

    return (
        <div
            className="game-browser-container"
            style={{ backgroundImage: `url(${BackgroundImage})` }}
        >
            <div className="game-browser-body">
                <div className="game-browser">
                    <div className="game-browser-create">
                        <CreateGame />
                    </div>
                    <div className="game-browser-join">
                        <JoinGame />
                    </div>
                </div>
                <div className="game-browser">
                    <div className="game-browser-profile">
                        <Profile player={currentPlayer} />
                    </div>
                    <div className="game-browser-profile">
                        <PlayersOnline />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default GameBrowser;
