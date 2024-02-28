import React from "react";
import BrowserListTile from "./BrowserListTile";
import "./BrowserList.css";

const BrowserList = ({ headers, data }) => {
    return (
        <div className="browser-list">
            <div className="browser-list-body">
                <div className="browser-list-headers">
                    {headers.map((header, index) => (
                        <div key={index}>
                            <p>{header}</p>
                        </div>
                    ))}
                </div>
                <div className="browser-list-items scroll-bar">
                    {data.map((item, index) => (
                        <div key={index}>
                            <BrowserListTile value={item} />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default BrowserList;
