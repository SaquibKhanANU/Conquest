package com.Game.conquest.engine.board;

import com.Game.conquest.engine.enumTypes.AgeType;
import com.Game.conquest.engine.enumTypes.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MilitaryStore {
    private int totalMilitary = 0;
    private int victoryTokens;
    private int defeatTokens;

    private Map<AgeType, Integer> pointsPerAge;
    public MilitaryStore(Map<AgeType, Integer> pointsPerAge) {
        this.totalMilitary = 0;
        this.victoryTokens = 0;
        this.defeatTokens = 0;
        this.pointsPerAge = pointsPerAge;
    }

    public void addTotalMilitary(int militaryIncrease) {
        this.totalMilitary += militaryIncrease;
    }
    public void defeat() {
        this.defeatTokens += 1;
    }

    public void victory(AgeType age) {
        this.victoryTokens += pointsPerAge.get(age);
    }

    public int getTotalMilitaryPoints() {
        return victoryTokens - defeatTokens;
    }
}
