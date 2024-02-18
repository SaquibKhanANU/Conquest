package com.Game.conquest.server.services;


import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public void createOrUpdate(String playerId, String username) {
        playerRepository.createOrUpdate(playerId, username);
    }

    public boolean contains(String playerId) {
        return playerRepository.contains(playerId);
    }

    public void get(String playerId) {
        playerRepository.get(playerId);
    }

    public void remove(String playerId) {
        playerRepository.remove(playerId);
    }

    public Collection<Player> getList() {
        return playerRepository.getList();
    }


}
