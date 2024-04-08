import "./PlayerHand.css";
import React from "react";
import Card from "./Card";

const PlayerHand = ({ cards }) => {
    console.log(cards);
    return (
        <div className="player-hand-container">
            {(cards.playerHand).map((card, index) => {
                console.log(card.image)
                return <Card key={index} cardName={card.image} />;
            })}
        </div>
    );
};

export default PlayerHand;
