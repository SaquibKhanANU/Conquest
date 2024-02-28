package com.Game.conquest.server.services;

import com.Game.conquest.server.dataObjects.Game;
import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game create(Lobby lobby) {
        return gameRepository.create(lobby);
    }

    public Game get(long gameId) {
        return gameRepository.get(gameId);
    }

    public void remove(long gameId) {
        gameRepository.remove(gameId);
    }
    public Collection<Game> getList() {
        return gameRepository.getList();
    }
}
