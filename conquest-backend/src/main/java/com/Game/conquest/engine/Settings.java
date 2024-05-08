package com.Game.conquest.engine;

import com.Game.conquest.engine.enumTypes.AgeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class Settings {

    @Getter
    private static final int initialCoins = 3; // Set initialCoins directly here

    @Getter
    private static final Map<AgeType, Integer> pointsPerMilitaryVictory = new HashMap<>();
    static {
        pointsPerMilitaryVictory.put(AgeType.AGE_ONE, 1);
        pointsPerMilitaryVictory.put(AgeType.AGE_TWO, 3);
        pointsPerMilitaryVictory.put(AgeType.AGE_THREE, 5);
    }
}
