import "./PlayerHand.css";
import Card from "./Card";
import ChooseCard from "./ChooseCard";
import { useDispatch, useSelector } from "react-redux";
import { setSelectedCard } from "../../redux/actions/actions.ts";

const PlayerHand = ({ cards }) => {
    console.log(cards);
    const dispatch = useDispatch();
    const selectedCard = useSelector((state) => state.gameAction.selectedCard);
    const selectedAction = useSelector(
        (state) => state.gameAction.selectedAction
    );

    const handleCardClick = (card) => {
        dispatch(setSelectedCard(card));
    };

    const handleCloseOverlay = () => {
        dispatch(setSelectedCard(null));
    };

    return (
        <div className="player-hand-container">
            {cards.map((card, index) => {
                if (selectedCard && selectedCard === card) {
                    return (
                        <div key={index} onClick={() => handleCardClick(card)}>
                            <ChooseCard card={selectedCard.image} />
                        </div>
                    );
                } else {
                    return (
                        <div
                            className={`player-hand-card ${
                                card.isPlayable
                                    ? "player-hand-card-green"
                                    : "player-hand-card-red"
                            }`}
                            key={index}
                            onClick={() => handleCardClick(card)}
                        >
                            <Card cardName={card.image} />
                        </div>
                    );
                }
            })}
            {selectedAction !== null && (
                <div
                    className="player-hand-overlay"
                    onClick={handleCloseOverlay}
                ></div>
            )}
        </div>
    );
};

export default PlayerHand;
