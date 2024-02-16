package com.Game.conquest.server.repositories;

import com.Game.conquest.server.dataObjects.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PlayerRepository {
    private List<Player> players = new ArrayList<>();

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findById(Long id) {
        return players.stream().filter(player -> player.getPlayerId().equals(id)).findFirst();
    }

    public void save(Player player) {
        players.add(player);
    }

    public void deleteById(Long id) {
        players.removeIf(player -> player.getPlayerId().equals(id));
    }
}
