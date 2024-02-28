package com.Game.conquest.server.controllers;


import com.Game.conquest.server.services.GameService;
import com.Game.conquest.server.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameBrowserController {

    @Autowired
    private LobbyService lobbyService;
    @Autowired
    private GameService gameService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/lobbies/getLobbiesList")
    public void getLobbiesList() {
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyService.getList());
    }

    @MessageMapping("/lobbies/getGamesList")
    public void getGamesList() {
        messagingTemplate.convertAndSend("/topic/games", gameService.getList());
    }

}
