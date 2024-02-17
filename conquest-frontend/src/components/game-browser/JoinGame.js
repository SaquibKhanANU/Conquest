import "./JoinGame.css";
import { React } from "react";
import LobbyTile from "./LobbyTile";
import { useSelector } from "react-redux";

const JoinGame = () => {
    const lobbies = useSelector((state) => state.lobby.lobbies);

    return (
        <div className="join-game-container">
            <h1>JOIN GAME</h1>
            <div className="join-game-body">
                <div className="join-game-headers">
                    <div>
                        <p>LOBBY NAME</p>
                    </div>
                    <div>
                        <p>MAP</p>
                    </div>
                    <div>
                        <p>MODE</p>
                    </div>
                    <div>
                        <p>PLAYERS</p>
                    </div>
                    <div>
                        <p>PRIVATE</p>
                    </div>
                    <div>
                        <p>JOIN</p>
                    </div>
                </div>
                <div className="join-game-lobbies scroll-bar">
                    {lobbies.map((lobby) => (
                        <div key={lobby.lobbyId}>
                            <LobbyTile gameLobbyData={lobby} />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default JoinGame;
