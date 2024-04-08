package com.Game.conquest.server.dataObjects;

import com.Game.conquest.engine.data.definition.GameDefinition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Game {

    private long gameId;
    private Player gameOwner;
    private LobbyRules lobbyRules;
    private ArrayList<Player> gamePlayers;
    private com.Game.conquest.engine.Game game;

    public Game(Lobby lobby) {
        this.gameId = lobby.getLobbyId();
        this.gameOwner = lobby.getLobbyOwner();
        this.gamePlayers = new ArrayList<>(lobby.getLobbyPlayers());
        this.lobbyRules = new LobbyRules(lobby.getLobbyRules().getLobbyName(), lobby.getLobbyRules().isPrivate(), lobby.getLobbyRules().getMaxPlayers());
        try {
            this.game = new GameDefinition().createGame(lobby.getPlayerCivilizations());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePlayer(Player player) {
        gamePlayers.remove(player);
    }
}
