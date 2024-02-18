package com.Game.conquest.server.dataObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String playerName;
    private String playerId;

    public Player(String playerId, String playerName) {
        this.playerName = playerName;
        this.playerId = playerId;
    }
}
