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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@EnableScheduling
public class GameBrowserController {

    @Autowired
    private LobbyService lobbyService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayerRepository playerRepository;

    @MessageMapping("/lobbies/getLobbiesList")
    public void getLobbiesList() {
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyService.getList());
    }

    @MessageMapping("/lobbies/createLobby")
    public void createLobby(@Payload String gameDefinitionJson, Principal principal) throws Exception {
        LobbyRules lobbyRules = LobbyRulesConverter.fromJson(gameDefinitionJson);
        Player lobbyOwner = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.create(lobbyOwner, lobbyRules);
        joinLobby(lobby.getLobbyId(), principal);
    }

    @MessageMapping("/lobby/joinLobby")
    public void joinLobby(@Payload long lobbyId, Principal principal) {
        if (checkLobbyHasMaxPlayers(lobbyId)) {
            return;
        }
        Player player = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.get(lobbyId);
        synchronized (lobby) {
            lobby.addPlayer(player);
            player.setLobbyId(lobbyId);
        }
        messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/player/currentLobby", lobby);
        messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, lobby);
        getLobbiesList();
    }

    // TODO move this checks to a appropriate class

    public boolean checkLobbyHasMaxPlayers(Long lobbyId) {
        Lobby lobby = lobbyService.get(lobbyId);
        return lobby.getLobbyPlayers().size() == lobby.getLobbyRules().getMaxPlayers();
    }
}
