import "./GameBrowser.css";
import BackgroundImage from "../../resources/imgs/game_browser_background_img.png";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { useSession } from "../session/SessionContext";
import CreateGame from "./CreateGame";
import JoinGame from "./JoinGame";
import Profile from "./Profile";

const GameBrowser = () => {
    // const { session } = useSession();
    const username = useSelector((state) => state.user.username);
    const navigate = useNavigate();

    useEffect(() => {
        if (username === null) {
            navigate("/");
        }
    }, [navigate, username]);

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
                <div className="game-browser-profile">
                    <Profile />
                </div>
            </div>
        </div>
    );
};

export default GameBrowser;
