import "./Game.css";
import React from "react";
import { useSelector } from "react-redux";
import Table from "./Table";
import Cards from "./Cards";

const Game = () => {
    const data = {
        player47: { name: "Babylon", color: "blue", side: "A" },
        player120: { name: "Babylon", color: "orange", side: "B" },
        player49: { name: "Gizah", color: "orange", side: "B" },
        player30: { name: "Gizah", color: "orange", side: "B" },
    };

    const game = useSelector((state) => state.currentPlayer.currentGame);
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    return (
        <div
            className="game-background"
            style={{
                backgroundImage: `url(${process.env.PUBLIC_URL}/imgs/backgrounds/game-paper-texture-background.png)`,
            }}
        >
            <div className="game-container">
                <div className="game-body">
                    <Cards />
                    <Table
                        // boards={game.gameState.playerCivilizations}
                        boards={data}
                        currentPlayer={currentPlayer}
                    />
                </div>
            </div>
        </div>
    );
};

export default Game;
