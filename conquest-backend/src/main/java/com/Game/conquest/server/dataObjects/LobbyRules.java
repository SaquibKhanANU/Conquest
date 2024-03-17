package com.Game.conquest.server.dataObjects;

import com.Game.conquest.engine.Civilization;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LobbyRules {
    @JsonProperty("privacy")
    private boolean isPrivate;
    @JsonProperty("maxPlayers")
    private int maxPlayers;
    @JsonProperty("lobbyName")
    private String lobbyName;

    public LobbyRules(String LobbyName, boolean isPrivate, int maxPlayers) {
        this.isPrivate = isPrivate;
        this.maxPlayers = maxPlayers;
        this.lobbyName = LobbyName;
    }

    public List<Civilization> getCivilizations() {
        List<String> civilizationNames = Arrays.asList(
                "Alexandria", "Babylon", "Ephesos", "Gizah", "Halikarn",
                "Olympia", "Rhodos"
        );
        List<String> colors = Arrays.asList(
                "red", "blue", "green", "yellow", "purple", "orange", "pink"
        );
        List<Civilization> civilizations = new ArrayList<>();

        for (int i = 0; i < civilizationNames.size(); i++) {
            String name = civilizationNames.get(i);
            civilizations.add(new Civilization(name, colors.get(i), "A"));
        }
        return civilizations;
    }

    @JsonIgnore
    public int getTimeLimit() {
        return 180;
    }
}
