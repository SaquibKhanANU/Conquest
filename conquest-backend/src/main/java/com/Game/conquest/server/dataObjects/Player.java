package com.Game.conquest.server.dataObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class Player {
    private String playerName;
    private final UUID playerId;

    public Player(String playerName) {
        this.playerName = playerName;
        this.playerId = UUID.randomUUID();
    }
}
