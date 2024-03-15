package com.Game.conquest.server.dataObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Game {

    // TODO: FIX THIS CONSTRUCTOR
    private long gameId;
    private LobbyRules lobbyRules;
    private ArrayList<Player> gamePlayers;

    public Game(Lobby lobby) {
        this.gameId = lobby.getLobbyId();
        this.lobbyRules = new LobbyRules(lobby.getLobbyRules().getLobbyName(), lobby.getLobbyRules().isPrivate(), lobby.getLobbyRules().getMaxPlayers());
        this.gamePlayers = new ArrayList<>(lobby.getLobbyPlayers()); // Creating a new ArrayList of Players
    }

    public void removePlayer(Player player) {
        gamePlayers.remove(player);
    }
}
