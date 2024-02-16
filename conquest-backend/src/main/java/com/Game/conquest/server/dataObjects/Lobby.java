package com.Game.conquest.server.dataObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class Lobby {
    @JsonProperty("lobbyName")
    private String lobbyName;
    @JsonProperty("lobbyId")
    private UUID lobbyId;
    @JsonProperty("lobbyOwner")
    private Player lobbyOwner;
    @JsonProperty("currentPlayers")
    private List<Player> currentPlayers;
    @JsonProperty("maxPlayers")
    private int maxPlayers;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("map")
    private String map;
    @JsonProperty("privateLobby")
    private boolean isPrivate;

    public Lobby(String lobbyName, UUID lobbyId, Player lobbyOwner, List<Player> currentPlayers, int maxPlayers, String mode, String map, boolean isPrivate) {
        this.lobbyName = lobbyName;
        this.lobbyId = lobbyId;
        this.lobbyOwner = lobbyOwner;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = currentPlayers;
        this.mode = mode;
        this.map = map;
        this.isPrivate = isPrivate;
    }

    @SneakyThrows
    public static Lobby fromJson(String lobbyJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(lobbyJson, Lobby.class);
    }
}
