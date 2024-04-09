import "./Board.css";
import React from "react";
import { useSelector, useDispatch } from "react-redux";
import BuiltCards from "./BuiltCards";
import Points from "./Points";
import { useState } from "react";
import BuiltWonder from "./BuiltWonder";
import {
    setSelectedCard,
    setSelectedAction,
} from "../../redux/actions/actions.ts";

const Board = ({ playerId, board }) => {
    const dispatch = useDispatch();
    const [stages, setStages] = useState(board.stages);
    const selectedCard = useSelector((state) => state.gameAction.selectedCard);

    const handleBuildWonderClick = (stage) => {
        if (stage.buildable && selectedCard !== null) {
            console.log("Build Wonder Clicked");
            stage.built = true;
            setStages([...stages]);
            dispatch(setSelectedCard(null));
            dispatch(
                setSelectedAction({
                    action: "BUILD_WONDER",
                    card: selectedCard,
                    playerId: playerId,
                })
            );
        }
    };

    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const isCurrentPlayersBoard = "player120" === playerId; // TODO: change this to currentPlayer.playerId === playerId
    const { name, side } = board;
    const renderBoard = () => {
        return (
            <div
                key={`${name}-${side}`}
                className={` ${
                    isCurrentPlayersBoard
                        ? "board-container-current-player"
                        : "board-container"
                }`}
            >
                <div className="board-header-container">
                    <p className="board-player-id">{playerId}</p>
                    <div className="board-header-points">
                        <Points militaryPoints={5} coins={9} points={50} />
                    </div>
                </div>

                <div className="board-built-cards">
                    <BuiltCards isCurrentPlayersBoard={isCurrentPlayersBoard} />
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

                <div
                    className={`board-build-wonders ${
                        stages.length === 4 ? "board-wonders-width-4" : ""
                    }`}
                >
                    {stages.map((stage, index) =>
                        stage.built ? (
                            <div
                                className="board-build-wonder-card"
                                key={index}
                            >
                                <BuiltWonder />
                            </div>
                        ) : isCurrentPlayersBoard ? (
                            <button
                                key={index}
                                className={`board-build-wonder ${
                                    stage.buildable
                                        ? "board-build-wonder-green"
                                        : "board-build-wonder-red"
                                }`}
                                onClick={() => handleBuildWonderClick(stage)}
                            >
                                +
                            </button>
                        ) : (
                            <div
                                className="board-build-wonder-card"
                                key={index}
                            ></div>
                        )
                    )}
                </div>
            </div>
        );
    };

    return renderBoard();
};

export default Board;
