import "./LobbySettings.css";
import React from "react";
import civilizationsJson from "../../resources/jsonData/civilizations.json";
import { useSession } from "../global/contexts/SessionContext";

const LobbySettings = ({ currentLobby, currentPlayer }) => {
    const {
        lobbyRules,
        lobbyOwner,
        lobbyPlayers,
        playerCivilizations,
        countdown,
    } = currentLobby;
    const { playerId } = currentPlayer;
    const { lobbyName, maxPlayers, privacy } = lobbyRules;
    const { session } = useSession();
    const isPrivate = privacy ? "YES" : "NO";
    const civilization = playerCivilizations[playerId];

    const handleChooseCivilization = (civ) => {
        console.log("Choosing civilization...");
        session.chooseCivilization(civ);
    };

    const formatTimer = (time) => {
        const minutes = Math.floor(time / 60);
        const seconds = time % 60;
        return `${minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
    };

    const profileInfoData = [
        { label: "LOBBY NAME:", value: lobbyName },
        { label: "LOBBY OWNER:", value: lobbyOwner.playerName },
        {
            label: "MAX PLAYERS:",
            value: `${lobbyPlayers.length}/${maxPlayers}`,
        },
        { label: "PRIVATE:", value: isPrivate.toString() },
        { label: "TIME REMAINING:", value: formatTimer(countdown) },
    ];

    return (
        <div className="profile-container">
            <div className="profile-header">SETTINGS</div>
            <div className="profile-body">
                {profileInfoData.map((info, index) => (
                    <div className="profile-info" key={index}>
                        <p className="silver-text">{info.label}</p>
                        <p>{info.value}</p>
                    </div>
                ))}
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
                                    onClick={() =>
                                        handleChooseCivilization(civ)
                                    }
                                    style={{ color: civ.color }}
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
