package com.Game.conquest.server.dataObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

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


    @JsonIgnore
    public List<Civilization> getCivilizations() {
        return Arrays.asList(
                new Civilization("Byzantine", "purple"),
                new Civilization("Roman", "navy"),
                new Civilization("Persian", "gold"),
                new Civilization("Greek", "blue"),
                new Civilization("Mongol", "green"),
                new Civilization("Aztec", "lime"),
                new Civilization("Babylonian", "brown"),
                new Civilization("Egyptian", "orange"),
                new Civilization("Viking", "red")
        );
    }

    @JsonIgnore
    public int getTimeLimit() {
        return 180;
    }
}
