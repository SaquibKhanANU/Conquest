package com.Game.conquest.server.config;


import com.Game.conquest.server.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
@Slf4j
public class WebSocketEventListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayerService playerService;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String playerId = Objects.requireNonNull(event.getUser()).getName();
        playerService.remove(playerId);
        messagingTemplate.convertAndSend("/topic/players", playerService.getList());
    }
}
