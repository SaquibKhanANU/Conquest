import "./LobbyActions.css";
import React from "react";

const LobbyActions = () => {
    return (
        <div className="actions-lobby-container">
            <div className="lobby-api-actions">
                <button className="lobby-action-button green-hover">
                    Start Game &#10148;
                </button>

                <button className="lobby-action-button red-hover">
                    DISBAND Game &#10005;
                </button>
                <button className="lobby-action-button red-hover">
                    Leave Game <span className="arrow-left">&#10148;</span>
                </button>
            </div>
            <button className="lobby-action-button green-hover">
                Ready Up &#10003;
            </button>
        </div>
    );
};

export default LobbyActions;
