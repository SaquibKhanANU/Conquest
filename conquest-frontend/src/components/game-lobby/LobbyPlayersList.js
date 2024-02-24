import "./LobbyPlayersList.css";
import React from "react";
import LobbyPlayerTile from "./LobbyPlayerTile";

const LobbyPlayersList = ({ players, playersReady, playerCivilizations }) => {
    return (
        <div className="players-list-container">
            <h1>PLAYERS</h1>
            <div className="players-list-body">
                <div className="players-list-headers">
                    <div>
                        <p>PLAYER NAME</p>
                    </div>
                    <div>
                        <p>COUNTRY</p>
                    </div>
                    <div>
                        <p>STATUS</p>
                    </div>
                </div>
                <div className="players-list scroll-bar">
                    {players.map((player) => (
                        <div key={player.playerId}>
                            <LobbyPlayerTile
                                playerData={player}
                                ready={playersReady.includes(player.playerId)}
                                civilization={playerCivilizations[(player.playerId)]}
                            />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default LobbyPlayersList;
