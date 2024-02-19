package com.Game.conquest.server.services;


import com.Game.conquest.server.dataObjects.LobbyRules;
import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LobbyService {

    @Autowired
    private LobbyRepository lobbyRepository;

    public Lobby create(Player lobbyOwner, LobbyRules lobbyRules) {
        lobbyRepository.create(lobbyOwner, lobbyRules);
        return null;
    }

    public boolean contains(long lobbyId) {
        return lobbyRepository.contains(lobbyId);
    }

    public Lobby get(long lobbyId) {
        return lobbyRepository.get(lobbyId);
    }

    public void remove(long lobbyId) {
        lobbyRepository.remove(lobbyId);
    }

    public Collection<Lobby> getList() {
        return lobbyRepository.getList();
    }

}
