package com.Game.conquest.engine.deck;

import com.Game.conquest.engine.enumTypes.AgeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class Deck {
    private final Map<AgeType, List<Card>> cardsPerAge = new HashMap<>();

    public void deal(Map<String, Hand> playerHands, AgeType ageType) {
        List<Card> currentAgeCards = cardsPerAge.get(ageType);
        int numPlayers = playerHands.size();
        int cardsPerPlayer = currentAgeCards.size() / numPlayers;

        if (currentAgeCards.size() % numPlayers != 0) {
            throw new IllegalArgumentException("Number of cards is not divisible evenly among players");
        }

        Collections.shuffle(currentAgeCards);
        int index = 0;

        for (Map.Entry<String, Hand> entry : playerHands.entrySet()) {
            List<Card> playerHand = new ArrayList<>(currentAgeCards.subList(index * cardsPerPlayer, (index + 1)
                    * cardsPerPlayer));
            entry.getValue().setPlayerHand(playerHand);
            index++;
        }
    }

    public void addAge(List<Card> cards, AgeType age) {
        cardsPerAge.put(age, cards);
    }
    public List<Card> getAge(AgeType age) {
        return cardsPerAge.get(age);
    }

}
