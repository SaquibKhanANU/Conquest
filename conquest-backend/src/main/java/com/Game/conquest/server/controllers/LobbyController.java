package com.Game.conquest.server.controllers;

import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import com.Game.conquest.server.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class LobbyController {
    private LobbyService lobbyService;
    private SimpMessagingTemplate messagingTemplate;
    private PlayerRepository playerRepository;

    @Autowired
    public LobbyController(LobbyService lobbyService, SimpMessagingTemplate messagingTemplate,
                                 PlayerRepository playerRepository) {
        this.lobbyService = lobbyService;
        this.messagingTemplate = messagingTemplate;
        this.playerRepository = playerRepository;
    }

    @MessageMapping("/lobby/leaveLobby")
    public void leaveLobby(@Payload long lobbyId, Principal principal) {
        Player player = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.get(lobbyId);
        synchronized (lobby) {
            lobby.removePlayer(player);
            if (lobby.getLobbyPlayers().isEmpty()) {
                lobbyService.remove(lobbyId);
            }
        }
        messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, lobby);
    }
}
