package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.enumTypes.ScienceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ScienceAbility extends InstantAbility {
    private ScienceType science;
    public ScienceAbility(String science) {
        this.science = ScienceType.valueOf(science.toUpperCase());
    }
}
