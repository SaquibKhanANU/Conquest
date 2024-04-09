import "./BuiltCards.css";
import React from "react";
import Card from "./Card";

const BuiltCards = ({ cards, isCurrentPlayersBoard }) => {
    return (
        <div
            className={`built-cards-container ${
                isCurrentPlayersBoard ? "built-cards-container-current-player" : ""
            }`}
        >
            <div className="built-cards-section brown">
                <div className="built-cards-card card-1">
                    <Card cardName="quarry.png" />
                </div>
                <div className="built-cards-card card-1">
                    <Card cardName="quarry.png" />
                </div>
            </div>
            <div className="built-cards-section silver">
                <div className="built-cards-card card-1">
                    <Card cardName="loom.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="loom.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="loom.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="loom.png" />
                </div>
            </div>
            <div className="built-cards-section yellow">
                <div className="built-cards-card card-1">
                    <Card cardName="arena.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="arena.png" />
                </div>
                <div className="built-cards-card card-1">
                    <Card cardName="arena.png" />
                </div>
            </div>
            <div className="built-cards-section red">
                <div className="built-cards-card card-1">
                    <Card cardName="archeryrange.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="archeryrange.png" />
                </div>
            </div>
            <div className="built-cards-section green">
                <div className="built-cards-card card-1">
                    <Card cardName="academy.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="academy.png" />
                </div>
            </div>
            <div className="built-cards-section blue">
                <div className="built-cards-card card-1">
                    <Card cardName="aqueduct.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="aqueduct.png" />
                </div>
                <div className="built-cards-card card-2">
                    <Card cardName="scientistsguild.png" />
                </div>
            </div>
        </div>
    );
};

export default BuiltCards;
