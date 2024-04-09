import React from "react";
import Card from "./Card";
import "./ChooseCard.css";
import { useSelector, useDispatch } from "react-redux";
import {
    setSelectedCard,
    setSelectedAction,
} from "../../redux/actions/actions.ts";

const ChooseCard = ({ card }) => {
    const dispatch = useDispatch();
    // const selectedCard = useSelector((state) => state.gameAction.selectedCard);
    // const selectedAction = useSelector(
    //     (state) => state.gameAction.selectedAction
    // );
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const handleCardClick = (neighbour) => {
        dispatch(setSelectedCard(null));
        dispatch(
            setSelectedAction({
                action: "BUILD_CARD",
                card: card,
                playerId: currentPlayer.playerId,
            })
        );
    };

    return (
        <div className="overlay-content" onClick={(e) => e.stopPropagation()}>
            <div className="choose-card-arrow-left">
                <div className="choose-card-cost">
                    <p>2</p>
                    <img
                        src={process.env.PUBLIC_URL + "/imgs/tokens/coin.png"}
                        alt="coins"
                    />
                </div>
                <p
                    className="choose-card-arrow left-arrow"
                    onClick={() => handleCardClick("LEFT")}
                >
                    &#10144;
                </p>
            </div>
            <div className="choose-card-arrow-right">
                <p
                    className="choose-card-arrow"
                    onClick={() => handleCardClick("RIGHT")}
                >
                    &#10144;
                </p>
                <div className="choose-card-cost">
                    <p>2</p>
                    <img
                        src={process.env.PUBLIC_URL + "/imgs/tokens/coin.png"}
                        alt="coins"
                    />
                </div>
            </div>
            <div className="choose-card-arrow-bottom">
                <p
                    className="choose-card-arrow"
                    onClick={() => handleCardClick("SELF")}
                >
                    &#10144;
                </p>
                <div className="choose-card-cost">
                    <p>1</p>
                    <img
                        src={process.env.PUBLIC_URL + "/imgs/tokens/coin.png"}
                        alt="coins"
                    />
                </div>
            </div>
            <div className="choose-card-wonder-upgrade">
                <img
                    src={
                        process.env.PUBLIC_URL +
                        "/imgs/tokens/wonder-upgrade.png"
                    }
                    alt="wonder-upgrade"
                />
            </div>
            <div className="choose-card-discard">
                <img
                    src={process.env.PUBLIC_URL + "/imgs/tokens/discard.png"}
                    alt="wonder-upgrade"
                />
                <img
                    src={process.env.PUBLIC_URL + "/imgs/tokens/discardx.png"}
                    alt="wonder-upgrade"
                />
            </div>
            <Card cardName={card} />
        </div>
    );
};

export default ChooseCard;
