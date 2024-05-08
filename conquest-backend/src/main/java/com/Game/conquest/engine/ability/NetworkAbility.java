package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.board.Board;
import com.Game.conquest.engine.enumTypes.CardType;
import com.Game.conquest.engine.enumTypes.NeighbourType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class NetworkAbility extends InstantAbility {
    private List<NeighbourType> boards;
    private String type;
    private int gold;
    private int points;
    private List<CardType> colors;

    @Override
    public void calculatePoints(Board board) {

    }

    @Override
    public void applyAbility(Board board) {

    }
}
