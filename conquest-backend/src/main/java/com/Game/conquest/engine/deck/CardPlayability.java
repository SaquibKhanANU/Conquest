package com.Game.conquest.engine.deck;

import com.Game.conquest.engine.util.Pair;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardPlayability {
    private Pair<Boolean, Integer> self;
    private Pair<Boolean, Integer> right;
    private Pair<Boolean, Integer> left;

    public CardPlayability() {
        this.self = new Pair<>(false, -1);
        this.right = new Pair<>(false, -1);
        this.left = new Pair<>(false, -1);
    }
}
