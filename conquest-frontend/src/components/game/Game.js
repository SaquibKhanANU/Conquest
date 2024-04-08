import "./Game.css";
import React from "react";
import { useSelector } from "react-redux";
import Table from "./Table";
import PlayerHand from "./PlayerHand";

const Game = () => {
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
                    <PlayerHand cards={game.game.deckManager.playerHands[currentPlayer.playerId]} />
                    {/* <Table
                        boards={game.gameState.playerCivilizations}
                        currentPlayer={currentPlayer}
                    /> */}
                </div>
            </div>
        </div>
    );
};

export default Game;
