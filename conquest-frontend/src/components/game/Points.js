import "./Points.css";
import React from "react";

const Points = ({ militaryPoints, coins, points }) => {
    return (
        <div className="points-container">
            <div className="points-item red">
                <p>{militaryPoints}</p>
                <img
                    src={process.env.PUBLIC_URL + "/imgs/tokens/victory1.png"}
                    alt="military points"
                ></img>
            </div>
            <div className="points-item gold">
                <p>{coins}</p>
                <img
                    src={process.env.PUBLIC_URL + "/imgs/tokens/coin.png"}
                    alt="coins"
                ></img>
            </div>
            <div className="points-item blue">
                <p>{points}</p>
                <img
                    src={
                        process.env.PUBLIC_URL + "/imgs/tokens/laurel-blue.png"
                    }
                    alt="total points"
                ></img>
            </div>
        </div>
    );
};

export default Points;
