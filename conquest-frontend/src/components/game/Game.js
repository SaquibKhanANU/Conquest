import "./Game.css";
import React from "react";

const Game = () => {
    return (
        <div
            className="game-container"
            style={{
                backgroundImage: `url(${process.env.PUBLIC_URL}/imgs/backgrounds/game-paper-texture-background.png)`,
            }}
        ></div>
    );
};

export default Game;
