import "./ChooseNameForm.css";
import React, { useState } from "react";
import ConquestClient from "../../client/ConquestClient.js";
import { useSession } from "../session/SessionContext.js"; // Import the useSession hook
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

const ChooseNameForm = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { updateSession } = useSession();

    const [name, setName] = useState("");
    const [isFocused, setIsFocused] = useState(false);

    async function connectToServer() {
        const conquestClient = new ConquestClient(
            "http://localhost:8080/conquest"
        );
        try {
            const session = await conquestClient.connect();
            return session;
        } catch (error) {
            console.error("Error connecting/choosing name:", error);
        }
    }

    async function handleSubmit(event) {
        event.preventDefault();
        if (name.length >= 3) {
            const session = await connectToServer();
            await session.subscribe(dispatch);
            await session.savePlayer(name);
            updateSession(session);
            navigate("/gameBrowser");
            console.log("Connected to server:", session);
        }
    }

    const handleChange = (event) => {
        setName(event.target.value);
    };

    const handleFocus = () => {
        setIsFocused(true);
    };

    const handleBlur = () => {
        setIsFocused(false);
    };

    return (
        <div className="name-form-container">
            <div className="name-form-title">WELCOME</div>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder={isFocused ? "" : "Enter your name"}
                    value={name}
                    onChange={handleChange}
                    onFocus={handleFocus}
                    onBlur={handleBlur}
                    autoComplete="off"
                    className="name-input"
                    maxLength={12}
                    minLength={3}
                />
                <button type="submit">
                    <p>&#8594;</p>
                </button>
            </form>
        </div>
    );
};

export default ChooseNameForm;
