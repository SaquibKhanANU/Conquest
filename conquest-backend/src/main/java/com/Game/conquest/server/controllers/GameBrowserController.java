package com.Game.conquest.server.controllers;


import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.repositories.LobbyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameBrowserController {

    private final LobbyRepository lobbyRepository;
    private final SimpMessagingTemplate messagingTemplate;


    public GameBrowserController(LobbyRepository lobbyRepository, SimpMessagingTemplate messagingTemplate) {
        this.lobbyRepository = lobbyRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/createLobby")
    public void createLobby(@Payload String lobbyJson) {
        Lobby lobby = Lobby.fromJson(lobbyJson);
        lobbyRepository.save(lobby);
        getLobbiesList();

    }

    @MessageMapping("/joinLobby")
    public void joinLobby() {
        // Join a lobby
    }

    @MessageMapping("/deleteLobby")
    public void deleteLobby() {
        // Delete a lobby
    }

    @MessageMapping("/getLobbies")
    public void getLobbiesList() {
        // Get all lobbies
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyRepository.findAll());
    }

}
