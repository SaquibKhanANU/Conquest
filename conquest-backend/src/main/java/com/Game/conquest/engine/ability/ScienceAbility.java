package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.board.Board;
import com.Game.conquest.engine.board.ScienceStore;
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

    @Override
    public void calculatePoints(Board board) {

    }

    @Override
    public void applyAbility(Board board) {
        ScienceStore scienceStore = board.getScienceStore();
        scienceStore.addScienceType(science);
    }
}
