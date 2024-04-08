package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpecialAbility extends InstantAbility {
    private String action;
    public SpecialAbility(String action) {
        this.action = action;
    }
}
