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
    private List<CardPlayability> cardPlayability;

    public Hand(List<Card> playerHand) {
        this.playerHand = playerHand;
        this.cardPlayability = new ArrayList<>();
    }

    public void switchCardToPlayableSelf(Card card, Integer cost) {
        int index = playerHand.indexOf(card);
        CardPlayability cardPlayability = this.cardPlayability.get(index);
        cardPlayability.getSelf().setFirst(true);
        cardPlayability.getSelf().setSecond(cost);
    }
}
