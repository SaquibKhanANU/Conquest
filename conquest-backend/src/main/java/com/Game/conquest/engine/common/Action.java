package com.Game.conquest.engine.common;

import com.Game.conquest.engine.deck.Card;
import com.Game.conquest.engine.enumTypes.ActionType;
import com.Game.conquest.engine.enumTypes.NeighbourType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Action {
    private ActionType actionType;
    private int index;
    private String playerId;
    private NeighbourType neighbourType;
}
