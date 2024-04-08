package com.Game.conquest.engine.deck;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Hand {
    private List<Card> playerHand;

    public Hand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }
}
