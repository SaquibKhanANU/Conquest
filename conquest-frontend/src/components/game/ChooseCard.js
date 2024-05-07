import React from "react";
import Card from "./Card";
import "./ChooseCard.css";
import { useSelector, useDispatch } from "react-redux";
import {
    setSelectedCard,
    setSelectedAction,
} from "../../redux/actions/actions.ts";
import { useSession } from "../global/contexts/SessionContext.js";

const ChooseCard = ({ card, cardPlayability }) => {
    const dispatch = useDispatch();
    // const selectedCard = useSelector((state) => state.gameAction.selectedCard);
    // const selectedAction = useSelector(
    //     (state) => state.gameAction.selectedAction
    // );
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    const { session } = useSession();

    const handleCardClick = () => {
        dispatch(setSelectedCard(null));
        const action = {
            actionType: "PLAY_CARD",
            card: card,
            playerId: currentPlayer.playerId,
            neighbourType: "SELF",
        };
        dispatch(setSelectedAction(action));
        session.sendGameAction(action);

        console.log(
           action
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
                    onClick={false ? () => handleCardClick("LEFT") : undefined}
                >
                    &#10144;
                </p>
            </div>
            <div className="choose-card-arrow-right">
                <p
                    className="choose-card-arrow"
                    onClick={false ? () => handleCardClick("RIGHT") : undefined}
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
                    className={`choose-card-arrow ${
                        cardPlayability.self ? "green-arrow" : "red-arrow"
                    }`}
                    onClick={
                        cardPlayability.self
                            ? () => handleCardClick("SELF")
                            : undefined
                    }
                >
                    &#10144;
                </p>
            </div>
            {/* <div className="choose-card-wonder-upgrade">
                <img
                    src={
                        process.env.PUBLIC_URL +
                        "/imgs/tokens/wonder-upgrade.png"
                    }
                    alt="wonder-upgrade"
                />
            </div> */}
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
            <Card cardName={card.image} />
        </div>
    );
};

export default ChooseCard;
