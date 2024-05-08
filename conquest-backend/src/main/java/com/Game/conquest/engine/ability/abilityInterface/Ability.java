package com.Game.conquest.engine.ability.abilityInterface;

import com.Game.conquest.engine.board.Board;

public interface Ability {
    void applyAbility(Board board);
    void calculatePoints(Board board);
}
