package com.Game.conquest.engine.board;

import com.Game.conquest.engine.enumTypes.ScienceType;

import java.util.HashMap;
import java.util.Map;

public class ScienceStore {
    private Map<ScienceType, Integer> sciences;

    public ScienceStore() {
        this.sciences = new HashMap<>();
    }

    public void addScienceType(ScienceType scienceType) {
        sciences.compute(scienceType, (type, count) -> count == null ? 1 : count + 1);
    }

    private void computeMaximumPoints() {

    }
}
