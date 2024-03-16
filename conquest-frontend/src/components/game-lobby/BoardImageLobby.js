import React from "react";

const BoardImageLobby = ({ currentLobby, currentPlayer, player }) => {
    const { playerCivilizations } = currentLobby;
    let { name, side } = playerCivilizations[currentPlayer.playerId];
    if (playerCivilizations[player.playerId]) {
        ({ name, side } = playerCivilizations[player.playerId]);
    }
    return (
        <div className="conquest-container">
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
        </div>
    );
};

export default BoardImageLobby;
