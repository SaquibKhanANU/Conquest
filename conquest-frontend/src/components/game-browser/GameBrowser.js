import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const GameBrowser = () => {
    const username = useSelector((state) => state.user.username);
    const navigate = useNavigate();

    // Effect to navigate back to home page when username becomes null
    useEffect(() => {
        if (username === null) {
            navigate("/");
        }
    }, [navigate, username]);

    return (
        <div className="game-browser-container">
            <h1>Welcome to the game of CONQUSET, {username}!</h1>
        </div>
    );
};

export default GameBrowser;
