import "./LobbySettings.css";
import React, { useState } from "react";
import civilizationsJson from "../../resources/jsonData/civilizations.json";

const LobbySettings = () => {
    const [civilization, setCivilization] = useState(civilizationsJson[0]);

    return (
        <div className="profile-container">
            <div className="profile-header">SETTINGS</div>
            <div className="profile-body">
                <div className="profile-info">
                    <p className="silver-text">LOBBY NAME:</p>
                    <p>Lobby</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">MAP:</p>
                    <p>SMALL</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">MODE:</p>
                    <p>CLASSIC</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">PRIVATE:</p>
                    <p>NO</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">CIVILIZATION:</p>
                    <div className="dropdown choose-civ-dropdown">
                        <button
                            className="dropbtn choose-civ"
                            style={{ color: civilization.color }}
                        >
                            {civilization.name}
                        </button>
                        <div className="dropdown-content">
                            {civilizationsJson.map((civ) => (
                                <p
                                    key={civ.name}
                                    onClick={() => setCivilization(civ)}
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
