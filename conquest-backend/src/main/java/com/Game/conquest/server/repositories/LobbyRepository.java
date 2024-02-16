package com.Game.conquest.server.repositories;


import com.Game.conquest.server.dataObjects.Lobby;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LobbyRepository {
    private List<Lobby> lobbies = new ArrayList<>();

    public List<Lobby> findAll() {
        return lobbies;
    }

    public Optional<Lobby> findById(int id) {
        return lobbies.stream().filter(lobby -> lobby.getLobbyId().equals(id)).findFirst();
    }

    public void save(Lobby lobby) {
        lobbies.add(lobby);
    }

    public void deleteById(int id) {
        lobbies.removeIf(lobby -> lobby.getLobbyId().equals(id));
    }
}
