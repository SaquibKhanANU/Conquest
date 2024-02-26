import "./PlayersOnline.css";
import React, { useEffect } from "react";
import PlayerOnlineTile from "./PlayerOnlineTile";
import { useSelector } from "react-redux";
import { useSession } from "../contexts/SessionContext";

const PlayersOnline = () => {
    const { session } = useSession();
    const players = useSelector((state) => state.lobby.players);

    useEffect(() => {
        if (session) {
            console.log("Fetching players...");
            session.getPlayersList();
        }
    }, [session]);

    const handleRefresh = () => {
        if (session) {
            console.log("Refreshing players...");
            session.getPlayersList();
        }
    };

    return (
        <div className="players-online-container">
            <div className="players-online-header">
                <div>PLAYERS ONLINE</div>
                {/* <div>
                    <button onClick={handleRefresh}>&#10227;</button>
                </div> */}
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
