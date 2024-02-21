package com.Game.conquest.server.controllers;


import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Collection;

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
    @SendToUser("/queue/player/currentPlayer")
    public Player createOrUpdatePlayer(@Payload String playerName, Principal principal) {
        String playerId = principal.getName();
        return playerService.createOrUpdate(playerId, playerName);
    }

    @MessageMapping("/players/removePlayer")
    public void removePlayer(Principal principal) {
        String playerId = principal.getName();
        playerService.remove(playerId);
    }
}
