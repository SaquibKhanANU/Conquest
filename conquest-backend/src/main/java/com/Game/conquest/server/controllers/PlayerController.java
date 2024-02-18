package com.Game.conquest.server.controllers;


import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.Collection;
import java.util.Objects;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/players/getPlayersList")
    @SendTo("/topic/players")
    public Collection<Player> getPlayersList() {
        return playerService.getList();
    }

    @MessageMapping("/players/createOrUpdatePlayer")
    public void createOrUpdatePlayer(@Payload String playerName, Principal principal) {
        String playerId = principal.getName();
        playerService.createOrUpdate(playerId, playerName);
    }

    @MessageMapping("/players/removePlayer")
    public void removePlayer(Principal principal) {
        String playerId = principal.getName();
        playerService.remove(playerId);
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String playerId = Objects.requireNonNull(event.getUser()).getName();
        playerService.remove(playerId);
        getPlayersList();
    }
}
