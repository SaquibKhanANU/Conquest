import "./Cards.css";
import React from "react";
import Card from "./Card";

const Cards = ({ cards }) => {
    return (
        <div className="cards-container">
            <Card cardName="quarry"></Card>
            <Card cardName="loom"></Card>
            <Card cardName="arena"></Card>
            <Card cardName="archeryrange"></Card>
            <Card cardName="academy"></Card>
            <Card cardName="altar"></Card>
            <Card cardName="scientistsguild"></Card>
        </div>
    );
};

export default Cards;
