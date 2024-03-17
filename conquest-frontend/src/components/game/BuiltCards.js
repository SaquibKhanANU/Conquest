import "./BuiltCards.css";
import React from "react";
import Card from "./Card";

const BuiltCards = ({ cards, isCurrentPlayersBoard }) => {
    return (
        <div
            className={`built-cards-container ${
                isCurrentPlayersBoard ? "built-cards-current-player" : ""
            }`}
        >
            <div className="built-cards-section brown">
                <div className="built-cards-card card-1">
                    <Card cardName="quarry" />
                </div>
                <div className="built-cards-card card-1">
                    <Card cardName="quarry" />
                </div>
            </div>
            <div className="built-cards-section silver">
                <div className="built-cards-card card-1">
                    <Card cardName="loom" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="loom" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="loom" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="loom" />
                </div>
            </div>
            <div className="built-cards-section yellow">
                <div className="built-cards-card card-1">
                    <Card cardName="arena" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="arena" />
                </div>
                <div className="built-cards-card card-1">
                    <Card cardName="arena" />
                </div>
            </div>
            <div className="built-cards-section red">
                <div className="built-cards-card card-1">
                    <Card cardName="archeryrange" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="archeryrange" />
                </div>
            </div>
            <div className="built-cards-section green">
                <div className="built-cards-card card-1">
                    <Card cardName="academy" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="academy" />
                </div>
            </div>
            <div className="built-cards-section blue">
                <div className="built-cards-card card-1">
                    <Card cardName="aqueduct" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="aqueduct" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="scientistsguild" />
                </div>
            </div>
        </div>
    );
};

export default BuiltCards;
