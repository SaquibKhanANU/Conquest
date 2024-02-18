import "./PlayersOnline.css";
import React, { useEffect, useCallback } from "react";
import PlayerOnlineTile from "./PlayerOnlineTile";
import { useSelector } from "react-redux";
import { useSession } from "../session/SessionContext";

const PlayersOnline = () => {
    const { session } = useSession();
    const players = useSelector((state) => state.lobby.players);

    const fetchPlayers = async () => {
        console.log("Fetching players...");
        await session.getPlayersList();
    };

    const fetchPlayersCallback = useCallback(async () => {
        if (session) {
            console.log("Fetching players...");
            fetchPlayers();
        }
    }, []);

    useEffect(() => {
        fetchPlayersCallback();
        const intervalId = setInterval(fetchPlayersCallback, 30000);
        return () => clearInterval(intervalId);
    }, [fetchPlayersCallback]);

    const handleRefresh = () => {
        if (session) {
            console.log("Refreshing players...");
            fetchPlayers();
        }
    };

    return (
        <div className="players-online-container">
            <div className="players-online-header">
                <div>PLAYERS ONLINE</div>
                <div>
                    <button onClick={handleRefresh}>&#10227;</button>
                </div>
            </div>
            <div className="players-online-body scroll-bar">
                {players.map((player) => (
                    <div key={player.playerId}>
                        <PlayerOnlineTile username={player.playerName} />
                    </div>
                ))}
            </div>
        </div>
    );
};

export default PlayersOnline;
