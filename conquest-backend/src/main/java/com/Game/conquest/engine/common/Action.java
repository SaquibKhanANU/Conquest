package com.Game.conquest.engine.common;

import com.Game.conquest.engine.deck.Card;
import com.Game.conquest.engine.enumTypes.ActionType;
import com.Game.conquest.engine.enumTypes.NeighbourType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Action {
    private ActionType actionType;
    private Card card;
    private String playerId;
    private NeighbourType neighbourType;
}
