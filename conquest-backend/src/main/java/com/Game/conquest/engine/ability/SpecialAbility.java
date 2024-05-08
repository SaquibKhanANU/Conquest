package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpecialAbility extends InstantAbility {
    private String action;
    public SpecialAbility(String action) {
        this.action = action;
    }

    @Override
    public void calculatePoints(Board board) {

    }

    @Override
    public void applyAbility(Board board) {

    }
}
