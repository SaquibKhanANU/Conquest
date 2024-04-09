import "./Card.css";
import React from "react";

const Card = ({ cardName }) => {
    const renderCard = () => {
        return (
            <img
                className="card-image"
                src={process.env.PUBLIC_URL + `/imgs/cards/front/${cardName}`}
                alt={cardName}
            />
        );
    };
    return <div className="card-container">{renderCard()}</div>;
};

export default Card;
