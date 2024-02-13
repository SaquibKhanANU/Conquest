import "./ChooseNameForm.css";
import React, { useState } from "react";

const ChooseNameForm = () => {
    const [name, setName] = useState("");
    const [isFocused, setIsFocused] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(name);
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
        <div class="name-form-container">
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
