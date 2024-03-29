package com.Game.conquest.server.dataObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String playerName;
    private String playerId;
    @JsonIgnore
    private long lobbyId;
    @JsonIgnore
    private long gameId;

    public Player(String playerId, String playerName) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.lobbyId = -1;
        this.gameId = -1;
    }

    public boolean isInLobby() {
        return lobbyId > -1;
    }

    public boolean isInGame() {
        return gameId > -1;
    }
}
