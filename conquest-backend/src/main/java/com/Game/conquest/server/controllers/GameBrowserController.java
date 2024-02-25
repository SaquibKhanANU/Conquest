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
import java.util.Timer;

@Controller
@EnableScheduling
public class GameBrowserController {

    @Autowired
    private LobbyService lobbyService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private LobbyController lobbyController;

    @MessageMapping("/lobbies/getLobbiesList")
    public void getLobbiesList() {
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyService.getList());
    }

    @MessageMapping("/lobbies/createLobby")
    public void createLobby(@Payload String gameDefinitionJson, Principal principal) throws Exception {
        LobbyRules lobbyRules = LobbyRulesConverter.fromJson(gameDefinitionJson);
        Player lobbyOwner = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.create(lobbyOwner, lobbyRules);
        startCountdown(lobby.getLobbyId(), principal);
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

    @MessageMapping("/lobby/getTimer")
    public void startCountdown(@Payload long lobbyId, Principal principal) {
        Lobby lobby = lobbyService.get(lobbyId);
        Timer timer = new Timer();
        lobby.setTimer(timer);
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                synchronized (lobby) {
                    messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, lobby);
                    lobby.decrementCountdown();
                    if (lobby.getCountdown() < 0) {
                        timer.cancel();
                        if (!lobby.isLobbyReady() && lobby.getLobbyPlayers().size() >= 1) {
                            lobbyController.disbandLobby(lobbyId, principal);
                        } else if (lobby.isLobbyReady()) {
                            System.out.println("Lobby is ready to start");
                        }
                    }
                }
            }
        }, 0, 1000);

    }


    // TODO move this checks to a appropriate class

    public boolean checkLobbyHasMaxPlayers(Long lobbyId) {
        Lobby lobby = lobbyService.get(lobbyId);
        return lobby.getLobbyPlayers().size() == lobby.getLobbyRules().getMaxPlayers();
    }

}
