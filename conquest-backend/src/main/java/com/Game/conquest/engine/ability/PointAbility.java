package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PointAbility extends InstantAbility {
    private Integer points;

    public PointAbility(int points) {
        this.points = points;
    }

    @Override
    public void calculatePoints(Board board) {
    }

    @Override
    public void applyAbility(Board board) {
        board.addPoints(this.points);
    }
}
