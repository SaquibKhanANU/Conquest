package com.Game.conquest.engine;

import com.Game.conquest.engine.board.BoardManager;
import com.Game.conquest.engine.common.Action;
import com.Game.conquest.engine.deck.DeckManager;
import com.Game.conquest.engine.enumTypes.AgeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.HashMap;


@Getter
public class Game {
    private final DeckManager deckManager;
    private final BoardManager boardManager;

    @JsonIgnore
    private HashMap<String, Action> currentTurnInfo;

    public Game(DeckManager deckManager, BoardManager boardManager) {
        this.deckManager = deckManager;
        this.boardManager = boardManager;
        this.currentTurnInfo = new HashMap<>();
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

    public void receiveAction(Action action, String playerId) {
        System.out.println("Received action from player: " + playerId + " action: " + action.getActionType());
        currentTurnInfo.put(playerId, action);
        if (isTurnComplete()) {
            this.playTurn();
            this.startNewTurn();
        }
    }

    private boolean isTurnComplete() {
        return currentTurnInfo.size() == deckManager.getPlayerHands().size();
    }
}
