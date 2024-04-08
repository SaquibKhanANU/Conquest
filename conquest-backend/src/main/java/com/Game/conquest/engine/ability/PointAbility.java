package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PointAbility extends InstantAbility {
    private Integer points;

    public PointAbility(int points) {
        this.points = points;
    }
}
