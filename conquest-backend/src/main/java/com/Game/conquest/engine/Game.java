package com.Game.conquest.engine;

import com.Game.conquest.engine.board.BoardManager;
import com.Game.conquest.engine.common.Action;
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

    private AgeType ageType = AgeType.AGE;

    public void startGame() {
        this.startNewAge();
        this.startNewTurn();
    }

    private void startNewAge() {
        this.ageType = this.ageType.getNextAge();
        this.deckManager.deal(this.ageType);
    }

    private void startNewTurn() {
        // this.deckManager.rotatePlayerHands();
        this.deckManager.clearCardPlayability();
        this.boardManager.findPlayableCards(deckManager.getPlayerHands());
    }

    private void playAction(Action action) {
    }

    public void playTurn() {

    }
}
