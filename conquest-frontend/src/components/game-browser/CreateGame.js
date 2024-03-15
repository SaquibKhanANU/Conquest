import "./CreateGame.css";
import React, { useState } from "react";
import { useSession } from "../global/contexts/SessionContext";
import DropdownOption from "../global/basic/DropdownOption";

const CreateGame = () => {
    const [isPrivate, setIsPrivate] = useState("NO");
    const [maxPlayers, setMaxPlayers] = useState(3);
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
                                maxLength={12}
                                minLength={1}
                            />
                        </div>
                    </div>
                    <DropdownOption
                        label="Num Players:"
                        currentValue={maxPlayers}
                        setValue={setMaxPlayers}
                        options={[3, 4, 5, 6, 7]}
                    />
                    <DropdownOption
                        label="PRIVATE:"
                        currentValue={isPrivate}
                        setValue={setIsPrivate}
                        options={["NO", "YES"]}
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
