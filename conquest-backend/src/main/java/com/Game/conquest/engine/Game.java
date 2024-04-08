package com.Game.conquest.engine;

import com.Game.conquest.engine.board.BoardManager;
import com.Game.conquest.engine.deck.DeckManager;
import com.Game.conquest.engine.enumTypes.AgeType;
import lombok.Getter;


@Getter
public class Game {
    private final DeckManager deckManager;
    private final BoardManager boardManager;

    public Game(DeckManager deckManager, BoardManager boardManager) {
        this.deckManager = deckManager;
        this.boardManager = boardManager;
    }

    private AgeType ageType;

    public void startGame() {
        ageType = AgeType.AGE_ONE;
        deckManager.deal(ageType);
    }

    private void startNewAge() {
        ageType = ageType.getNextAge();
    }

    public void playTurn() {

    }
}
