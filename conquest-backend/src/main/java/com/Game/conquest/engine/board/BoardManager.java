package com.Game.conquest.engine.board;

import com.Game.conquest.engine.deck.Card;
import com.Game.conquest.engine.deck.CardPlayability;
import com.Game.conquest.engine.deck.Hand;
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
}
