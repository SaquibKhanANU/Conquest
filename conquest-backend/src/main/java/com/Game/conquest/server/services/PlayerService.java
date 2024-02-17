package com.Game.conquest.server.services;


import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public void associateSession(String userId, SimpMessageHeaderAccessor headerAccessor) {
        playerRepository.associateSession(userId, headerAccessor);
    }
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public void save(Player player) {
        playerRepository.save(player);
    }

    public void deleteById(UUID id) {
        playerRepository.deleteById(id);
    }

    public Player findById(UUID id) {
        return playerRepository.findById(id);
    }

    public UUID findPlayerIdBySessionId(String sessionId) {
        return playerRepository.findPlayerIdBySessionId(sessionId);
    }

    public void deleteBySessionId(String sessionId) {
        playerRepository.deleteBySessionId(sessionId);
    }
}
