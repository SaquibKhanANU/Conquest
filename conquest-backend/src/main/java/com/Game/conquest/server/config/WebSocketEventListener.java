package com.Game.conquest.server.config;


import com.Game.conquest.server.controllers.GameController;
import com.Game.conquest.server.controllers.LobbyController;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Component
@Slf4j
public class WebSocketEventListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private LobbyController lobbyController;
    @Autowired
    private GameController gameController;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        Principal principal = event.getUser();
        if (principal == null) {
            log.error("Principal is null");
            return;
        }
        Player player = playerService.get(principal.getName());
        if (player == null) {
            log.error("Player is null");
            return;
        }
        if (player.isInLobby()) {
            lobbyController.leaveLobby(player.getLobbyId(), principal);
        }
        if (player.isInGame()) {
            gameController.leaveGame(player.getGameId(), principal);
        }
        playerService.remove(player.getPlayerId());
        messagingTemplate.convertAndSend("/topic/players", playerService.getList());
    }
}
