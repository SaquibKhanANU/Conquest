import "./LobbyPlayersList.css";
import React from "react";
import LobbyPlayerTile from "./LobbyPlayerTile";

const LobbyPlayersList = ({ currentLobby, currentPlayer, setPlayer }) => {
    const { lobbyPlayers, playersReady, playerCivilizations, lobbyOwner } =
        currentLobby;
    return (
        <div className="players-list-container">
            <h1>PLAYERS</h1>
            <div className="players-list-body">
                <div className="players-list-headers">
                    <p>PLAYER NAME</p>
                    <p>COUNTRY</p>
                    <p>STATUS</p>
                </div>
                <div className="players-list scroll-bar">
                    {lobbyPlayers.map((player) => (
                        <div
                            key={player.playerId}
                            onClick={() => setPlayer(player)}
                        >
                            <LobbyPlayerTile
                                playerData={player}
                                ready={playersReady.includes(player.playerId)}
                                civilization={
                                    playerCivilizations[player.playerId]
                                }
                                isOwner={
                                    lobbyOwner.playerId ===
                                    currentPlayer.playerId
                                }
                            />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default LobbyPlayersList;
