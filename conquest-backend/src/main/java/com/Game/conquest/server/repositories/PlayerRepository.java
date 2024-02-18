package com.Game.conquest.server.repositories;

import com.Game.conquest.server.dataObjects.Player;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PlayerRepository {
    private Map<String, Player> players = new ConcurrentHashMap<>();

    public Player createOrUpdate(String playerId, String username) {
        Player player = players.computeIfAbsent(playerId, k -> new Player(playerId, username));
        player.setPlayerId(playerId);
        player.setPlayerName(username);
        return player;
    }

    public boolean contains(String playerId) {
        return players.containsKey(playerId);
    }

    public Player get(String playerId) {
        Player player = players.get(playerId);
        if (player == null) {
            throw new NoSuchElementException("Player not found for ID: " + playerId);
        }
        return player;
    }

    public void remove(String playerId) {
        players.remove(playerId);
    }

    public Collection<Player> getList() {
        return players.values();
    }

}
