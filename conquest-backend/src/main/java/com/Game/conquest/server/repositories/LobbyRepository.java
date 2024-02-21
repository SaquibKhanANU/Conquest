package com.Game.conquest.server.repositories;


import com.Game.conquest.server.dataObjects.LobbyRules;
import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.dataObjects.Player;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LobbyRepository {
    private Map<Long, Lobby> lobbies = new ConcurrentHashMap<>();

    private AtomicLong currentLobbyId = new AtomicLong(0);

    public Lobby create(Player lobbyOwner, LobbyRules lobbyRules) {
        long lobbyId = currentLobbyId.getAndIncrement();
        Lobby lobby = new Lobby(lobbyId, lobbyOwner, lobbyRules);
        lobbies.put(lobbyId, lobby);
        return lobby;
    }

    public boolean contains(long lobbyId) {
        return lobbies.containsKey(lobbyId);
    }
    public Lobby get(long lobbyId) {
        if (!this.contains(lobbyId)) {
            throw new NoSuchElementException("Lobby not found for ID: " + lobbyId);
        }
        return lobbies.get(lobbyId);
    }

    public void remove(long lobbyId) {
        lobbies.remove(lobbyId);
    }

    public Collection<Lobby> getList() {
        return lobbies.values();
    }

}
