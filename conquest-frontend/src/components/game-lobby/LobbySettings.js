import "./LobbySettings.css";
import React, { useState } from "react";
import civilizationsJson from "../../resources/jsonData/civilizations.json";
import { useSession } from "../global/contexts/SessionContext";

const LobbySettings = ({
    lobbyRules,
    lobbyOwner,
    lobbyPlayersLength,
    civilization,
}) => {
    const { session } = useSession();
    const { lobbyName, map, maxPlayers, mode, privacy } = lobbyRules;
    const { playerName } = lobbyOwner;
    const isPrivate = privacy ? "YES" : "NO";

    const handleChooseCivilization = (civ) => {
        console.log("Choosing civilization...");
        session.chooseCivilization(civ);
    };

    return (
        <div className="profile-container">
            <div className="profile-header">SETTINGS</div>
            <div className="profile-body">
                <div className="profile-info">
                    <p className="silver-text">LOBBY NAME:</p>
                    <p>{lobbyName}</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">LOBBY OWNER:</p>
                    <p>{playerName}</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">MAP:</p>
                    <p>{map}</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">MODE:</p>
                    <p>{mode}</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">MAX PLAYERS:</p>
                    <p>
                        {lobbyPlayersLength}/{maxPlayers}
                    </p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">PRIVATE:</p>
                    <p>{isPrivate}</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">TIME REMAINING:</p>
                    <p>5:00</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">CIVILIZATION:</p>
                    <div className="dropdown choose-civ-dropdown">
                        {civilization && (
                            <button
                                className="dropbtn choose-civ"
                                style={{ color: civilization.color }}
                            >
                                {civilization.name}
                            </button>
                        )}
                        <div className="dropdown-content">
                            {civilizationsJson.map((civ) => (
                                <p
                                    key={civ.name}
                                    onClick={handleChooseCivilization.bind(
                                        this,
                                        civ
                                    )}
                                    style={{ color: civ.color }} // Applying color style
                                >
                                    {civ.name}
                                </p>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LobbySettings;
