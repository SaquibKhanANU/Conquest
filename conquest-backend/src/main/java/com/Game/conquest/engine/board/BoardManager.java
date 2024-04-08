package com.Game.conquest.engine.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class BoardManager {
    private Map<String, Board> playerBoards;

    public BoardManager(Map<String, Board> playerBoards) {
        this.playerBoards = playerBoards;
    }
}
