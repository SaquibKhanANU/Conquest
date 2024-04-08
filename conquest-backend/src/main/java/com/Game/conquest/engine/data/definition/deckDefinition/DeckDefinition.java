package com.Game.conquest.engine.data.definition.deckDefinition;

import com.Game.conquest.engine.deck.Deck;
import com.Game.conquest.engine.enumTypes.AgeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Getter
public class DeckDefinition {
    private Map<AgeType, AgeDefinition> deck;

    public Deck create(int numPlayers) {
        Deck newDeck = new Deck();
        for (Map.Entry<AgeType, AgeDefinition> entry : deck.entrySet()) {
            newDeck.addAge(entry.getValue().create(numPlayers), entry.getKey());
        }
        return newDeck;
    }
}
