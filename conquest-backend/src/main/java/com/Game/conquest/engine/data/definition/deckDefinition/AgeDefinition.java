package com.Game.conquest.engine.data.definition.deckDefinition;

import com.Game.conquest.engine.deck.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Getter
public class AgeDefinition {
    private List<CardDefinition> cards;
    private String backImage;
    private List<CardDefinition> guildCards;

    public List<Card> create(int numPlayers) {
        List<Card> newCards = new java.util.ArrayList<>();
        for (CardDefinition cardDefinition : cards) {
            newCards.addAll(cardDefinition.create(backImage, numPlayers));
        }
        if (guildCards != null) {
            List<Card> newGuildCards = chooseGuildCards(numPlayers);
            newCards.addAll(newGuildCards);
        }
        return newCards;
    }

    public List<Card> chooseGuildCards(int numPlayers) {
        List<CardDefinition> shuffledGuildCards = new ArrayList<>(this.guildCards);
        List<Card> newGuildCards = new ArrayList<>();
        Collections.shuffle(shuffledGuildCards);
        for (CardDefinition cardDefinition : shuffledGuildCards) {
            newGuildCards.add(cardDefinition.create(backImage));
        }
        return newGuildCards.subList(0, numPlayers + 2);
    }

}
