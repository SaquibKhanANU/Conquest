package com.Game.conquest.engine.deck;

import com.Game.conquest.engine.enumTypes.AgeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class DeckManager {
    private Deck deck;
    private Map<String, Hand> playerHands;
    public DeckManager(Deck deck, Set<String> players) {
        this.deck = deck;
        this.playerHands = new HashMap<>();
        for (String player : players) {
            playerHands.put(player, new Hand(new ArrayList<>()));
        }
    }
    public void deal(AgeType ageType) {
        deck.deal(playerHands, ageType);
    }

    public void rotatePlayerHands(int direction) {
        if (playerHands == null || playerHands.isEmpty()) {
            return;
        }
        List<Hand> handList = new ArrayList<>(playerHands.values());
        Collections.rotate(handList, direction);
        int index = 0;
        for (String key : playerHands.keySet()) {
            playerHands.put(key, handList.get(index++));
        }
    }

    public void clearCardPlayability() {
        for (Map.Entry<String, Hand> entry : playerHands.entrySet()) {
            entry.getValue().getCardPlayabilityList().clear();
        }
    }

    public Card playCardByIndex(String playerId, int index) {
        Hand hand = playerHands.get(playerId);
        Card card = hand.getCardByIndex(index);
        hand.removeCardByIndex(index);
        return card;
    }

    public boolean checkLastCardInAge() {
        for (Hand hand : playerHands.values()) {
            if (hand.getPlayerHand().size() == 1) {
                return true;
            }
        }
        return false;
    }


}
