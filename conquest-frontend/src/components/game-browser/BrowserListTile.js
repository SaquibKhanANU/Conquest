import "./BrowserListTile.css";
import React from "react";

const BrowserListTile = ({ value }) => {
    return (
        <div className="browser-list-tile-container">
            <div className="browser-list-tile-body">
                {Object.keys(value).map((key) => (
                    <div key={key}>
                        <p>{value[key]}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default BrowserListTile;
