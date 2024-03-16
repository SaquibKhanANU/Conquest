import "./BrowserListContainer.css";
import React, { useState, useEffect } from "react";
import BrowserList from "./BrowserList";
import { useSelector } from "react-redux";
import { useSession } from "../global/contexts/SessionContext";

const BrowserListContainer = () => {
    //TODO: Fix this so it only retrives data once and not more than one time
    const { session } = useSession();
    const [showFirstComponent, setShowFirstComponent] = useState(true);

    useEffect(() => {
        if (session) {
            console.log("Fetching lobbies...");
            session.getLobbiesList();
            session.getGamesList();
        }
    }, [session]);

    const handleInProgressClick = () => {
        console.log("In Progress clicked...");
        setShowFirstComponent((prevState) => !prevState);
    };

    const handleJoinLobby = async (lobbyId) => {
        session.joinLobby(lobbyId);
    };

    const lobbies = useSelector((state) => state.lobby.lobbies);
    const games = useSelector((state) => state.lobby.games);

    const headersGamesList = ["LOBBY NAME", "PLAYERS"];
    const headersLobbiesList = [
        "LOBBY NAME",
        "PLAYERS",
        "PRIVATE",
        "JOIN",
    ];

    const lobbiesData = lobbies.map((lobby) => ({
        lobbyName: lobby.lobbyRules.lobbyName,
        players: `${lobby.lobbyPlayers.length}/${lobby.lobbyRules.maxPlayers}`,
        isPrivate: lobby.lobbyRules.isPrivate ? "Yes" : "No",
        join: (
            <button
                className="join-game-button"
                onClick={() => handleJoinLobby(lobby.lobbyId)}
            >
                &#10148;
            </button>
        ),
    }));

    const gamesData = games.map((game) => ({
        lobbyName: game.lobbyRules.lobbyName,
        players: `${game.gamePlayers.length}/${game.lobbyRules.maxPlayers}`,
    }));

    return (
        <div className="browser-list-container">
            <div className="browser-list-header">
                {showFirstComponent ? <h1>JOIN GAME</h1> : <h1>IN PROGRESS</h1>}
                {showFirstComponent ? (
                    <button
                        onClick={handleInProgressClick}
                        className="browser-list-button-right"
                    >
                        In Progress &#10148;
                    </button>
                ) : (
                    <button
                        onClick={handleInProgressClick}
                        className="browser-list-button-left"
                    >
                        <span className="arrow-left">&#10148;</span> Lobbies
                    </button>
                )}
            </div>
            {showFirstComponent ? (
                <BrowserList headers={headersLobbiesList} data={lobbiesData} />
            ) : (
                <BrowserList headers={headersGamesList} data={gamesData} />
            )}
        </div>
    );
};

export default BrowserListContainer;
