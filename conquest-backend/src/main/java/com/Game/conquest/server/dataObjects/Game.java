package com.Game.conquest.server.dataObjects;

import com.Game.conquest.engine.GameState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@NoArgsConstructor
public class Game {

    // TODO: FIX THIS CONSTRUCTOR to add game state
    private long gameId;
    private Player gameOwner;
    private LobbyRules lobbyRules;
    private ArrayList<Player> gamePlayers;
    private GameState gameState;

    public Game(Lobby lobby) {
        this.gameId = lobby.getLobbyId();
        this.gameOwner = lobby.getLobbyOwner();
        this.gamePlayers = new ArrayList<>(lobby.getLobbyPlayers());
        this.lobbyRules = new LobbyRules(lobby.getLobbyRules().getLobbyName(), lobby.getLobbyRules().isPrivate(), lobby.getLobbyRules().getMaxPlayers());
        this.gameState = new GameState(gamePlayers, new ConcurrentHashMap<>(lobby.getPlayerCivilizations()));
    }

    public void removePlayer(Player player) {
        gamePlayers.remove(player);
    }
}
