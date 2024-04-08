package com.Game.conquest.engine.board;

import com.Game.conquest.engine.ability.abilityInterface.Ability;
import com.Game.conquest.engine.common.Cost;
import lombok.Getter;

import java.util.List;
@Getter
public class WonderStage {
    private Cost cost;
    private List<Ability> abilities;

    public WonderStage(Cost cost, List<Ability> abilities) {
        this.cost = cost;
        this.abilities = abilities;
    }
}
