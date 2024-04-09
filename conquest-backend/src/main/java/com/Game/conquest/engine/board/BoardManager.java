package com.Game.conquest.engine.board;

import com.Game.conquest.engine.deck.Hand;
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

    public void findPlayableCards(Map<String, Hand> playerHands) {

    }
}
