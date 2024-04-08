import "./Card.css";
import React from "react";

const Card = ({ cardName }) => {
    const renderCard = () => {
        return (
            <div className="card-container">
                <img
                    className="card-image"
                    src={
                        process.env.PUBLIC_URL +
                        `/imgs/cards/front/${cardName}`
                    }
                    alt={cardName}
                />
            </div>
        );
    };
    return <div>{renderCard()}</div>;
};

export default Card;
