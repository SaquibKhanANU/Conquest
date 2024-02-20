import "./LobbyPlayersList.css";
import React from "react";
import LobbyPlayerTile from "./LobbyPlayerTile";

const LobbyPlayersList = () => {
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
                    <LobbyPlayerTile playerData={{ playerName: "Player 1" }} />
                    {/* {lobbies.map((lobby) => (
                        <div key={lobby.lobbyId}>
                            <LobbyTile gameLobbyData={lobby} />
                        </div>
                    ))} */}
                </div>
            </div>
        </div>
    );
};

export default LobbyPlayersList;
