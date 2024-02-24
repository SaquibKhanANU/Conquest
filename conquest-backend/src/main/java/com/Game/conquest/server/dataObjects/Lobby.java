package com.Game.conquest.server.dataObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
public class Lobby {
    private long lobbyId;
    private Player lobbyOwner;
    private LobbyRules lobbyRules;
    private ArrayList<Player> lobbyPlayers;

    public Lobby(long lobbyId, Player lobbyOwner, LobbyRules lobbyRules) {
        this.lobbyId = lobbyId;
        this.lobbyOwner = lobbyOwner;
        this.lobbyRules = lobbyRules;
        this.lobbyPlayers = new ArrayList<>(lobbyRules.getMaxPlayers());
    }

    @Synchronized
    public void addPlayer(Player player) {
        lobbyPlayers.add(player);
    }

    @Synchronized
    public void removePlayer(Player player) {
        lobbyPlayers.remove(player);
    }

    @Synchronized
    public ArrayList<Player> getLobbyPlayers() {
        return lobbyPlayers;
    }

    @Synchronized
    public boolean isFull() {
        return lobbyPlayers.size() == lobbyRules.getMaxPlayers();
    }

}
