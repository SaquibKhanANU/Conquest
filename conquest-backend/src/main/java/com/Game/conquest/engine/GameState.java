package com.Game.conquest.engine;


import com.Game.conquest.server.dataObjects.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
@Getter
@Setter
public class GameState {
    private ArrayList<Player> gamePlayers;
    private Map<String, Civilization> playerCivilizations;
    public GameState(ArrayList<Player> gamePlayers, ConcurrentHashMap<String, Civilization> playerCivilizations) {
        this.gamePlayers = gamePlayers; // convert to PlayerState
        this.playerCivilizations = playerCivilizations; // Convert this to Boards e.g. wonder stages, num wonders etc
    }

    private void randomizeBoards() {

    }

    private void randomizeCards() {

    }

}
