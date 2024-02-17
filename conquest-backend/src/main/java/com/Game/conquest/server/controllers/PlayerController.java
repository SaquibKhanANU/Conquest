package com.Game.conquest.server.controllers;


import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;
import java.util.UUID;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/players/getPlayers")
    @SendTo("/topic/players")
    public List<Player> findAllPlayers() {
        // Logic to retrieve players
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    @SendTo("/topic/players")
    public Player findById(UUID id) {
        return playerService.findById(id);
    }

    @MessageMapping("/players/savePlayer")
    public void save(@Payload String playerName, SimpMessageHeaderAccessor headerAccessor) {
        Player player =  new Player(playerName);
        playerService.save(player);
    }

    @DeleteMapping("/{id}")
    public void deleteById(UUID id) {
        playerService.deleteById(id);
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        UUID userId = playerService.findPlayerIdBySessionId(sessionId);
        if (userId != null) {
            playerService.deleteById(userId);
            playerService.deleteBySessionId(sessionId);
        }
        findAllPlayers();
    }
}
