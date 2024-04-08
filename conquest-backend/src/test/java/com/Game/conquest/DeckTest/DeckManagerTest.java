package com.Game.conquest.DeckTest;

import com.Game.conquest.server.dataObjects.Civilization;
import com.Game.conquest.engine.Game;
import com.Game.conquest.engine.data.definition.GameDefinition;
import com.Game.conquest.engine.enumTypes.AgeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeckManagerTest {
    // Test cases for DeckManager
    GameDefinition gameDefinition;
    Game game;

    @BeforeEach
    public void setUp() {
        try {
            Map<String, Civilization> playerCivilizations = new HashMap<>();
            playerCivilizations.put("Player1", new Civilization("Alexandria", "Red", "A"));
            playerCivilizations.put("Player2", new Civilization("Ephesos", "Blue", "B"));
            playerCivilizations.put("Player3", new Civilization("Gizah", "Green", "A"));
            gameDefinition = new GameDefinition();
            game = gameDefinition.createGame(playerCivilizations);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize GameDefinition", e);
        }
    }

    @Test
    public void testDeckManagerNotNull() {
        assertNotNull(game.getDeckManager(), "DeckManager should not be null");
    }

    @Test
    public void testDeckManagerContents() {
        game.getDeckManager().deal(AgeType.AGE_THREE);
        int expectedHandSize = 7;

        game.getDeckManager().getPlayerHands().forEach((player, hand) -> {
            int actualHandSize = hand.getPlayerHand().size();
            assertEquals(expectedHandSize, actualHandSize, "Hand size for " + player + " does not match expected size");
        });
    }

}
