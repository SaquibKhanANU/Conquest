package com.Game.conquest.server.repositories;

import com.Game.conquest.server.dataObjects.Game;
import com.Game.conquest.server.dataObjects.Lobby;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {
    private Map<Long, Game> games = new ConcurrentHashMap<>();
    public Game create(Lobby lobby) {
        Game game = new Game(lobby);
        games.put(game.getGameId(), game);
        return game;
    }

    public Game get(long gameId) {
        return games.get(gameId);
    }

    public void remove(long gameId) {
        games.remove(gameId);
    }
    public Collection<Game> getList() {
        return games.values();
    }

}
