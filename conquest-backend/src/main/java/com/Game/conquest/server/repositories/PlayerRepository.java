package com.Game.conquest.server.repositories;

import com.Game.conquest.server.dataObjects.Player;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PlayerRepository {
    HashMap<UUID, Player> players = new HashMap<>();
    private final Map<String, UUID> sessionUserMap = new ConcurrentHashMap<>();

    // Method to associate session ID with user ID
    public void associateSession(String userId, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        sessionUserMap.put(sessionId, UUID.fromString(userId));
    }

    public List<Player> findAll() {
        return new ArrayList<>(players.values());
    }

    public void save(Player player) {
        players.put(player.getPlayerId(), player);
    }

    public void deleteById(UUID id) {
        players.remove(id);
    }

    public Player findById(UUID id) {
        return players.get(id);
    }

    public UUID findPlayerIdBySessionId(String sessionId) {
        return sessionUserMap.get(sessionId);
    }

    public void deleteBySessionId(String sessionId) {
        sessionUserMap.remove(sessionId);
    }

}
