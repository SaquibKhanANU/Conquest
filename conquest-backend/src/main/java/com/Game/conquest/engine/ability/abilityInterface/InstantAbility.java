package com.Game.conquest.engine.ability.abilityInterface;

import com.Game.conquest.engine.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class InstantAbility implements Ability {
    @Override
    public abstract void applyAbility(Board board);
}
