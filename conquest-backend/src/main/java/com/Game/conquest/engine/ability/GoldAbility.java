package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoldAbility extends InstantAbility {
    private int gold;

    public GoldAbility(int gold) {
        this.gold = gold;
    }

    @Override
    public void calculatePoints(Board board) {

    }

    @Override
    public void applyAbility(Board board) {

    }
}
