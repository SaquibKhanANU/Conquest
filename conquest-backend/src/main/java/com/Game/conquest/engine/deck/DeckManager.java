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
}
