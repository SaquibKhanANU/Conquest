package com.Game.conquest.engine.board;

import lombok.Getter;

@Getter
public class Board {
    private Wonder wonder;

    public Board(Wonder wonder) {
        this.wonder = wonder;
    }
}
