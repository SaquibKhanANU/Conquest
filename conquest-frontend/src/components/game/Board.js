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
import ThreeWayArrow from "./ThreeWayArrow.js";

const Board = ({ playerId, board }) => {
    const { name, image } = board.wonder;
    const dispatch = useDispatch();
    const [stages, setStages] = useState(board.wonder.stages);
    const selectedCard = useSelector((state) => state.gameAction.selectedCard);

    // const handleBuildWonderClick = (stage) => {
    //     if (stage.buildable && selectedCard !== null) {
    //         stage.built = true;
    //         setStages([...stages]);
    //         dispatch(setSelectedCard(null));
    //         dispatch(
    //             setSelectedAction({
    //                 action: "BUILD_WONDER",
    //                 card: selectedCard,
    //                 playerId: playerId,
    //                 neighbourType: "SELF",
    //             })
    //         );
    //     }
    // };

    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const isCurrentPlayersBoard = currentPlayer.playerId === playerId; 
    const renderBoard = () => {
        return (
            <div
                key={`${name}-${playerId}`}
                className={` ${
                    isCurrentPlayersBoard
                        ? "board-container-current-player"
                        : "board-container"
                }`}
            >
                <div className="board-header-container">
                    <p className="board-player-id">{playerId}</p>
                    <div className="board-header-points">
                        <Points militaryPoints={0} coins={0} points={0} />
                    </div>
                </div>

                <div className="board-built-cards">
                    {/* <BuiltCards isCurrentPlayersBoard={isCurrentPlayersBoard} /> */}
                </div>
                {/* {currentPlayer.playerId === playerId && (
                    <div className="board-controls">
                        <button className="board-play-card">+</button>
                        <button className="board-discard-card">-</button>
                    </div>
                )} */}
                <img
                    className="board-image"
                    src={process.env.PUBLIC_URL + "/imgs/boards/" + image}
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
                        )
                        //  : isCurrentPlayersBoard && selectedCard ? (
                        //     <ThreeWayArrow />
                        //     // <button
                        //     //     key={index}
                        //     //     className={`board-build-wonder ${
                        //     //         stage.buildable
                        //     //             ? "board-build-wonder-green"
                        //     //             : "board-build-wonder-red"
                        //     //     }`}
                        //     //     onClick={() => handleBuildWonderClick(stage)}
                        //     // >
                        //     //     +
                        //     // </button>
                        // ) 
                        : (
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
