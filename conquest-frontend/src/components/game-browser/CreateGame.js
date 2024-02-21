import "./CreateGame.css";
import React, { useState } from "react";
import { useSession } from "../global/contexts/SessionContext";

const CreateGame = () => {
    const [map, setMap] = useState("MEDIUM (M)");
    const [mode, setMode] = useState("CLASSIC");
    const [isPrivate, setIsPrivate] = useState("NO");
    const [maxPlayers, setMaxPlayers] = useState(4);
    const [lobbyName, setLobbyName] = useState("");
    const { session } = useSession();

    const handleLobbyNameChange = (event) => {
        setLobbyName(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const privacy = isPrivate === "YES";
        const gameDefinitionJson = {
            map,
            mode,
            privacy,
            maxPlayers,
            lobbyName,
        };
        session.createLobby(gameDefinitionJson);
    };

    return (
        <div className="create-game-container">
            <h1>Create game</h1>
            <form onSubmit={handleSubmit}>
                <div className="create-game-body">
                    <div className="create-lobby-option">
                        <p>Lobby name:</p>
                        <div>
                            <input
                                type="text"
                                value={lobbyName}
                                onChange={handleLobbyNameChange}
                                maxLength={10}
                                minLength={1}
                            />
                        </div>
                    </div>
                    <div className="create-lobby-option">
                        <p>MAP:</p>
                        <div className="dropdown">
                            <button className="dropbtn">{map}</button>
                            <div className="dropdown-content">
                                <p onClick={() => setMap("SMALL (S)")}>
                                    SMALL (S)
                                </p>
                                <p onClick={() => setMap("MEDIUM (M)")}>
                                    MEDIUM (M)
                                </p>
                                <p onClick={() => setMap("LARGE (L)")}>
                                    LARGE (L)
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="create-lobby-option">
                        <p>MODE:</p>
                        <div className="dropdown">
                            <button className="dropbtn">{mode}</button>
                            <div className="dropdown-content">
                                <p onClick={() => setMode("CLASSIC")}>
                                    CLASSIC
                                </p>
                                <p onClick={() => setMode("KING OF THE HILL")}>
                                    KING OF THE HILL
                                </p>
                                <p onClick={() => setMode("DOMINATION")}>
                                    DOMINATION
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="create-lobby-option">
                        <p>PRIVATE:</p>
                        <div className="dropdown">
                            <button className="dropbtn">{isPrivate}</button>
                            <div className="dropdown-content">
                                <p onClick={() => setIsPrivate("NO")}>NO</p>
                                <p onClick={() => setIsPrivate("YES")}>YES</p>
                            </div>
                        </div>
                    </div>
                    <div className="create-lobby-option">
                        <p>Num Players:</p>
                        <div className="dropdown">
                            <button className="dropbtn">{maxPlayers}</button>
                            <div className="dropdown-content">
                                <p onClick={() => setMaxPlayers(2)}>2</p>
                                <p onClick={() => setMaxPlayers(3)}>3</p>
                                <p onClick={() => setMaxPlayers(4)}>4</p>
                                <p onClick={() => setMaxPlayers(5)}>5</p>
                            </div>
                        </div>
                    </div>
                    <div className="add-button">
                        <button type="submit">+</button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default CreateGame;
