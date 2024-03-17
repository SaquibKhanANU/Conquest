import "./BuiltWonder.css";
import React from "react";

const BuiltWonder = ({ card }) => {
    return (
        <div className="built-wonder-container">
            <img
                src={process.env.PUBLIC_URL + `/imgs/cards/back/age1.png`}
                alt="age1"
            />
        </div>
    );
};

export default BuiltWonder;
