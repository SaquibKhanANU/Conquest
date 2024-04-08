package com.Game.conquest.engine.data.definition.deckDefinition;

import com.Game.conquest.engine.data.definition.sharedDefinition.AbilityDefinition;
import com.Game.conquest.engine.deck.Card;
import com.Game.conquest.engine.common.Cost;
import com.Game.conquest.engine.enumTypes.CardType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@Getter
public class CardDefinition {
    private String name;
    private CardType cardType;
    private AbilityDefinition ability;
    private Cost cost;
    private List<String> parents;
    private List<String> children;
    private Map<Integer, Integer> countPerNPlayer;
    private String image;

    public List<Card> create(String backImage, int numPlayers) {
        int count = countPerNPlayer.getOrDefault(numPlayers, 0);
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards.add(create(backImage));
        }
        return cards;
    }

    public Card create(String backImage) {
        return new Card(name, cardType, cost, ability.create(), children, parents, image, backImage);
    };

}
