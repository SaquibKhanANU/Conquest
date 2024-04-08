package com.Game.conquest.GameLoopTest;

import com.Game.conquest.engine.Game;
import com.Game.conquest.engine.data.definition.GameDefinition;
import com.Game.conquest.engine.enumTypes.AgeType;
import com.Game.conquest.server.dataObjects.Civilization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewAgeTest {
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

    // Test cases for NewAge
    @Test
    public void testNewAge() {
        AgeType ageType = AgeType.AGE_ONE;
        assertEquals(AgeType.AGE_TWO, ageType.getNextAge());
        assertEquals(AgeType.AGE_THREE.getNextAge(), null);
    }
}
