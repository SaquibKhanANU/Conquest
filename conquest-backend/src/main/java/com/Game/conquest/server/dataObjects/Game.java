package com.Game.conquest.server.dataObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Game {
    private long gameId;

    @JsonIgnore
    private GameState gameState;

    public Game(long gameId, GameState gameState) {
        this.gameId = gameId;
        this.gameState = gameState;
    }

}
