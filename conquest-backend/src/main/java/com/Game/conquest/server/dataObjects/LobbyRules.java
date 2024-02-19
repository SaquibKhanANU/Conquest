package com.Game.conquest.server.dataObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LobbyRules {
    @JsonProperty("map")
    private String map;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("privacy")
    private boolean isPrivate;
    @JsonProperty("maxPlayers")
    private int maxPlayers;
    @JsonProperty("lobbyName")
    private String lobbyName;

    public LobbyRules(String LobbyName, String map, String mode, boolean isPrivate, int maxPlayers) {
        this.map = map;
        this.mode = mode;
        this.isPrivate = isPrivate;
        this.maxPlayers = maxPlayers;
        this.lobbyName = LobbyName;
    }
}
