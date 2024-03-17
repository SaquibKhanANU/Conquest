import "./Board.css";
import React from "react";
import { useSelector } from "react-redux";
import BuiltCards from "./BuiltCards";
import Points from "./Points";
import { useState } from "react";
import BuiltWonder from "./BuiltWonder";

const Board = ({ playerId, board }) => {
    const [images, setImages] = useState(Array(3).fill(false)); // Assuming 3 buttons

    const handleBuildWonderClick = (index) => {
        console.log("Build Wonder Clicked");
        const newImages = [...images];
        newImages[index] = true;
        setImages(newImages);
    };
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const { name, side } = board;
    const renderBoard = () => {
        return (
            <div
                key={`${name}-${side}`}
                className={` ${
                    currentPlayer.playerId === playerId
                        ? "board-container-current-player"
                        : "board-container"
                }`}
            >
                <div className="board-header-container">
                    <p className="board-player-id">{playerId}</p>
                    <Points militaryPoints={5} coins={9} points={50} />
                </div>

                <div className="board-built-cards">
                    <BuiltCards
                        isCurrentPlayersBoard={
                            currentPlayer.playerId === playerId
                        }
                    />
                </div>
                {currentPlayer.playerId === playerId && (
                    <div className="board-controls">
                        <button className="board-play-card">+</button>
                        <button className="board-discard-card">-</button>
                    </div>
                )}
                <img
                    className="board-image"
                    src={
                        process.env.PUBLIC_URL +
                        "/imgs/boards/" +
                        name.toLowerCase() +
                        side.toUpperCase() +
                        ".png"
                    }
                    alt="Board"
                />

                {/* TODO MAKE THIS ADAPT TO NUMBER OF WONDERS 3/4 (will change when board class is made) */}

                <div className="board-build-wonders">
                    {images.map((isImage, index) =>
                        isImage ? (
                            <BuiltWonder />
                        ) : (
                            // currentPlayer.playerId === playerId &&
                            <button
                                key={index}
                                className="board-build-wonder"
                                onClick={() => handleBuildWonderClick(index)}
                            >
                                +
                            </button>
                        )
                    )}
                </div>
            </div>
        );
    };

    return renderBoard();
};

export default Board;
