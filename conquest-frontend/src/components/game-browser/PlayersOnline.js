import "./PlayersOnline.css";
import React, { useEffect } from "react";
import PlayerOnlineTile from "./PlayerOnlineTile";
import { useSelector, useDispatch } from "react-redux";
import { useSession } from "../session/SessionContext";

const PlayersOnline = () => {
    const { session } = useSession();
    const players = useSelector((state) => state.lobby.players);
    const dispatch = useDispatch();

    useEffect(() => {
        const fetchPlayers = async () => {
            if (session) {
                console.log("Fetching players");
                await session.getPlayers(dispatch);
            }
        };
        fetchPlayers();
    }, []);

    return (
        <div className="players-online-container">
            <div>PLAYERS ONLINE</div>
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
