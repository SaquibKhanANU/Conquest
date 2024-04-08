package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MilitaryAbility extends InstantAbility {
    private int military;
    public MilitaryAbility(int military) {
        this.military = military;
    }
}
