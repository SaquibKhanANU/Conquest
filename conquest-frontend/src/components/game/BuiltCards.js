import "./BuiltCards.css";
import React from "react";
import Card from "./Card";

const BuiltCards = ({ cards, isCurrentPlayersBoard }) => {
    const groupedCards = cards.reduce((acc, card) => {
        const cardType = card.cardType.toLowerCase();
        if (!acc[cardType]) {
            acc[cardType] = [];
        }
        acc[cardType].push(card);
        return acc;
    }, {});

    const cardTypesOrder = ["brown", "grey", "yellow", "red", "green", "blue"];

    return (
        <div
            className={`built-cards-container ${
                isCurrentPlayersBoard ? "built-cards-container-current-player" : ""
            }`}
        >
            {cardTypesOrder.map((type) => (
                <div key={type} className={`built-cards-section ${type}`}>
                    {groupedCards[type]?.map((card, index) => (
                        <div key={index} className="built-cards-card">
                            <Card cardName={card.image} />
                        </div>
                    ))}
                </div>
            ))}
        </div>
    );
};

export default BuiltCards;
