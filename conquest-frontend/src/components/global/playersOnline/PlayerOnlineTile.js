import "./PlayerOnlineTile.css";
import React from "react";

const PlayerOnlineTile = ({ username }) => {
    return (
        <div className="player-online-tile-container">
            <div className="player-online-tile-body">
                <div className="player-online-tile-username">{username}</div>
                <div className="player-online-tile-button">
                    <button>+</button>
                </div>
            </div>
        </div>
    );
};

export default PlayerOnlineTile;
