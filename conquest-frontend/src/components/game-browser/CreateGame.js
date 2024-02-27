import "./CreateGame.css";
import React, { useState } from "react";
import { useSession } from "../global/contexts/SessionContext";
import DropdownOption from "../global/basic/DropdownOption";

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
        if (lobbyName.length === 0) {
            alert("Please enter a lobby name");
            return;
        }
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
                                maxLength={14}
                                minLength={1}
                            />
                        </div>
                    </div>
                    <DropdownOption
                        label="MAP:"
                        currentValue={map}
                        setValue={setMap}
                        options={["SMALL (S)", "MEDIUM (M)", "LARGE (L)"]}
                    />
                    <DropdownOption
                        label="MODE:"
                        currentValue={mode}
                        setValue={setMode}
                        options={["CLASSIC", "KING OF THE HILL", "DOMINATION"]}
                    />
                    <DropdownOption
                        label="PRIVATE:"
                        currentValue={isPrivate}
                        setValue={setIsPrivate}
                        options={["NO", "YES"]}
                    />
                    <DropdownOption
                        label="Num Players:"
                        currentValue={maxPlayers}
                        setValue={setMaxPlayers}
                        options={[2, 3, 4, 5]}
                    />
                    <div className="add-button">
                        <button type="submit">+</button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default CreateGame;
