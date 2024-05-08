package com.Game.conquest.engine.deck;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Hand {
    private List<Card> playerHand;
    private List<CardPlayability> cardPlayabilityList;

    public Hand(List<Card> playerHand) {
        this.playerHand = playerHand;
        this.cardPlayabilityList = new ArrayList<>();
    }


    public Card getCardByIndex(int index) {
        return playerHand.get(index);
    }

    public void removeCardByIndex(int index) {
        playerHand.remove(index);
    }
}
