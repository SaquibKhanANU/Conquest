import React from "react";
import Board from "./Board";
import "./Table.css"; // Import your CSS file for styling

const Table = ({ boards, currentPlayer }) => {
    const playerIdList = Object.keys(boards);
    return (
        <div className="table-container">
            <div className="board-table">
                {playerIdList.map((playerId) => (
                    <Board
                        key={playerId}
                        playerId={playerId} 
                        board={boards[playerId]}
                    />
                ))}
            </div>
        </div>
    );
};

export default Table;
