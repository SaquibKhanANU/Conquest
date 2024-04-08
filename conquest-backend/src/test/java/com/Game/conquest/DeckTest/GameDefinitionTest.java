package com.Game.conquest.DeckTest;

import com.Game.conquest.server.dataObjects.Civilization;
import com.Game.conquest.engine.board.Board;
import com.Game.conquest.engine.board.Wonder;
import com.Game.conquest.engine.data.definition.GameDefinition;
import com.Game.conquest.engine.deck.Deck;
import com.Game.conquest.engine.enumTypes.AgeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameDefinitionTest {

    GameDefinition gameDefinition;

    @BeforeEach
    public void setUp() {
        try {
            gameDefinition = new GameDefinition();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize GameDefinition", e);
        }
    }

    @Test
    public void testDeckNotNull() {
        assertNotNull(gameDefinition.getDeck(), "Deck should not be null");
    }

    @Test
    public void testWondersNotNull() {
        assertNotNull(gameDefinition.getWonders(), "Wonders should not be null");
    }

    @Test
    public void testDeckContents() {
        int initialDeckSize = gameDefinition.getDeck().getDeck().get(AgeType.AGE_THREE).getCards().size();
        Deck deck = gameDefinition.prepareDeck(7);
        int finalDeckSize = deck.getAge(AgeType.AGE_THREE).size();
        boolean deckSizeChanged = initialDeckSize != finalDeckSize;
        System.out.println("Initial deck size: " + initialDeckSize + ", Final deck size: " + finalDeckSize);
        assertTrue(deckSizeChanged,
                "Deck size should be different after preparation. Initial size: "
                        + initialDeckSize + ", Final size: " + finalDeckSize);
    }

    @Test
    public void checkDeckSizeModNumPlayers() {
        int numPlayers = 5;
        AgeType ageType = AgeType.AGE_THREE;
        Deck deck = gameDefinition.prepareDeck(numPlayers);
        int deckSize = deck.getAge(ageType).size();
        int expectedDeckSize = numPlayers * 7;
        assertEquals(expectedDeckSize, deckSize,
                "Deck size should be " + expectedDeckSize + " for " + numPlayers + " players. Actual size: " + deckSize);
    }

    @Test
    public void checkPlayerBoardInitialization() {
        Map<String, Civilization> playerCivilizations = new HashMap<>();
        playerCivilizations.put("Player1", new Civilization("Alexandria", "Red", "A"));
        playerCivilizations.put("Player2", new Civilization("Ephesos", "Blue", "B"));

        Map<String, Board> preparedBoard = gameDefinition.preparePlayerBoard(playerCivilizations);
        assertEquals(2, preparedBoard.size(),
                "Number of player boards should be equal to the number of players");
        Wonder wonder = preparedBoard.get("Player1").getWonder();
        assertEquals("Alexandria", wonder.getName(),
                "Player1's wonder name should be Alexandria");
        assertTrue(wonder.getStages().size() < 4, "Player1's wonder should have less than 4 stages");
    }
}
