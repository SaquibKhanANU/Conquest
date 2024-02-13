import "./ChooseNameForm.css";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
    requestChooseUsername,
    chooseUsernameSuccess,
} from "../../redux/actions.ts";

const ChooseNameForm = () => {
    const dispatch = useDispatch();
    const [name, setName] = useState("");
    const [isFocused, setIsFocused] = useState(false);

    const chooseUsername = (name) => {
        dispatch(requestChooseUsername(name));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        chooseUsername(name);
    };

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
                />
                <button type="submit">
                    <p>&#8594;</p>
                </button>
            </form>
        </div>
    );
};

export default ChooseNameForm;
