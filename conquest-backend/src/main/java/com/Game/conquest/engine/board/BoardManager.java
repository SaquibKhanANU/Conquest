package com.Game.conquest.engine.board;

import com.Game.conquest.engine.common.Action;
import com.Game.conquest.engine.deck.Card;
import com.Game.conquest.engine.deck.CardPlayability;
import com.Game.conquest.engine.deck.Hand;
import com.Game.conquest.engine.enumTypes.ActionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class BoardManager {
    private Map<String, Board> playerBoards;

    public BoardManager(Map<String, Board> playerBoards) {
        this.playerBoards = playerBoards;
    }

    public void findPlayableCards(Map<String, Hand> playerHands) {
        for (Map.Entry<String, Hand> entry : playerHands.entrySet()) {
            String player = entry.getKey();
            Hand hand = entry.getValue();
            for (Card card : hand.getPlayerHand()) {
                CardPlayability cardPlayability = new CardPlayability();
                cardPlayability.setSelf(playerBoards.get(player).canPlayCardSelf(card));
                hand.getCardPlayabilityList().add(cardPlayability);
            }
        }
    }

    public void playCardAndAction(String playerId, Card card, Action action) {
        Board board = playerBoards.get(playerId);

        // TODO: COMPUTE THE COST e.g. if self reduce the self gold by x else if neighbour/s etc. provide neighbour/s
        //  the gold
        switch (action.getActionType()) {
            case PLAY_CARD -> board.playCard(card);
            case BUILD_WONDER -> board.buildWonder(card);
            case DISCARD -> board.discardCard(card);
            default -> throw new IllegalArgumentException("Unexpected value: " + action.getActionType());
        }
    }
}
