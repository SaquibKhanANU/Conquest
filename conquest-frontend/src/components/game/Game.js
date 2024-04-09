import "./Game.css";
import React from "react";
import { useSelector } from "react-redux";
import Table from "./Table";
import PlayerHand from "./PlayerHand";

const Game = () => {
    // const game = useSelector((state) => state.currentPlayer.currentGame);
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    // const cards = game.game.deckManager.playerHands[currentPlayer.playerId];
    // const boards = game.gameState.playerCivilizations;

    const cardsTest = {
        playerHand: [
            {
                image: "academy.png",
                isPlayable: false,
            },
            {
                image: "aqueduct.png",
                isPlayable: false,
            },
            {
                image: "barracks.png",
                isPlayable: true,
            },
            {
                image: "baths.png",
                isPlayable: true,
            },
            {
                image: "circus.png",
                isPlayable: false,
            },
            {
                image: "courthouse.png",
                isPlayable: true,
            },
            {
                image: "gardens.png",
                isPlayable: false,
            },
        ],
    };

    const data = {
        player47: {
            name: "Ephesos",
            color: "blue",
            side: "A",
            stages: [
                { stageNumber: 1, buildable: true, built: true },
                { stageNumber: 2, buildable: false, built: false },
                { stageNumber: 3, buildable: false, built: false },
            ],
        },
        player120: {
            name: "Babylon",
            color: "orange",
            side: "B",
            stages: [
                { stageNumber: 1, buildable: true, built: true },
                { stageNumber: 2, buildable: true, built: false },
                { stageNumber: 3, buildable: false, built: false },
            ],
        },
        player49: {
            name: "Gizah",
            color: "orange",
            side: "B",
            stages: [
                { stageNumber: 1, buildable: true, built: true },
                { stageNumber: 2, buildable: true, built: false },
                { stageNumber: 3, buildable: false, built: false },
                { stageNumber: 4, buildable: false, built: false },
            ],
        },
        player30: {
            name: "Gizah",
            color: "orange",
            side: "B",
            stages: [
                { stageNumber: 1, buildable: true, built: true },
                { stageNumber: 2, buildable: false, built: false },
                { stageNumber: 3, buildable: false, built: false },
                { stageNumber: 4, buildable: false, built: false },
            ],
        },
    };

    return (
        <div
            className="game-background"
            style={{
                backgroundImage: `url(${process.env.PUBLIC_URL}/imgs/backgrounds/game-paper-texture-background.png)`,
            }}
        >
            <div className="game-container">
                <div className="game-body">
                    <PlayerHand
                        // cards={cards}
                        cards={cardsTest}
                    />
                    <Table boards={data} currentPlayer={currentPlayer} />
                </div>
            </div>
        </div>
    );
};

export default Game;
