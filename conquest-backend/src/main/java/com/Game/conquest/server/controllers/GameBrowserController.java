package com.Game.conquest.server.controllers;


import com.Game.conquest.server.converter.LobbyRulesConverter;
import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.dataObjects.LobbyRules;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import com.Game.conquest.server.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GameBrowserController {

    private LobbyService lobbyService;
    private SimpMessagingTemplate messagingTemplate;
    private PlayerRepository playerRepository;

    @Autowired
    public GameBrowserController(LobbyService lobbyService, SimpMessagingTemplate messagingTemplate,
                                 PlayerRepository playerRepository) {
        this.lobbyService = lobbyService;
        this.messagingTemplate = messagingTemplate;
        this.playerRepository = playerRepository;
    }

    @MessageMapping("/lobbies/getLobbiesList")
    public void getLobbiesList() {
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyService.getList());
    }

    @MessageMapping("/lobbies/createLobby")
    @SendToUser("/queue/player/currentLobby")
    public Lobby createLobby(@Payload String gameDefinitionJson, Principal principal) throws Exception {
        LobbyRules lobbyRules = LobbyRulesConverter.fromJson(gameDefinitionJson);
        Player lobbyOwner = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.create(lobbyOwner, lobbyRules);
        getLobbiesList();
        return lobby;
    }

    @MessageMapping("/lobbies/removeLobby")
    public void removeLobby(@Payload long lobbyId) {
        lobbyService.remove(lobbyId);
        getLobbiesList();
    }

    @MessageMapping("/lobby/joinLobby")
    public void joinLobby(@Payload long lobbyId, Principal principal) {
        Player player = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.get(lobbyId);
        synchronized (lobby) {
            lobby.addPlayer(player);
        }
        messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/player/currentLobby", lobby);
        messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, lobby);
    }
}
