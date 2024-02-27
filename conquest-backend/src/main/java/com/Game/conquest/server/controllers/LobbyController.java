package com.Game.conquest.server.controllers;

import com.Game.conquest.server.converter.LobbyRulesConverter;
import com.Game.conquest.server.dataObjects.Civilization;
import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.dataObjects.LobbyRules;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import com.Game.conquest.server.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Timer;

@Controller
public class LobbyController {

    @Autowired
    private LobbyService lobbyService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameBrowserController gameBrowserController;

    @MessageMapping("/lobby/createLobby")
    public void createLobby(@Payload String gameDefinitionJson, Principal principal) throws Exception {
        LobbyRules lobbyRules = LobbyRulesConverter.fromJson(gameDefinitionJson);
        Player lobbyOwner = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.create(lobbyOwner, lobbyRules);
        startCountdown(lobby.getLobbyId(), principal);
        joinLobby(lobby.getLobbyId(), principal);
    }

    @MessageMapping("/lobby/joinLobby")
    public void joinLobby(@Payload long lobbyId, Principal principal) {
        Lobby lobby = lobbyService.get(lobbyId);
        if (lobby.checkLobbyFull()) {
            return;
        }
        Player player = playerRepository.get(principal.getName());
        synchronized (lobby) {
            lobby.addPlayer(player);
            player.setLobbyId(lobbyId);
        }
        messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/player/currentLobby", lobby);
        messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, lobby);
        getLobbiesList();
    }

    @MessageMapping("/lobby/leaveLobby")
    public void leaveLobby(@Payload long lobbyId, Principal principal) {
        Player player = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.get(lobbyId);
        synchronized (lobby) {
            if (lobby.getLobbyPlayers().isEmpty() ||lobby.checkLobbyOwner(principal.getName())) {
                disbandLobby(lobbyId, principal);
                return;
            }
            player.setLobbyId(-1);
            lobby.removePlayer(player);
            messagingTemplate.convertAndSendToUser(player.getPlayerId(), "/queue/player/currentLobby", "{}");
        }
        messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, lobby);
    }

    @MessageMapping("/lobby/disbandLobby")
    public void disbandLobby(@Payload long lobbyId, Principal principal) {
        Lobby lobby = lobbyService.get(lobbyId);
        if (!lobby.checkLobbyOwner(principal.getName())) {
            return;
        }
        synchronized (lobby) {
            lobby.getLobbyPlayers().forEach(player -> player.setLobbyId(-1));
            lobby.getLobbyPlayers().forEach(player -> messagingTemplate.convertAndSendToUser(player.getPlayerId(),
                    "/queue/player/currentLobby", "{}"));
            lobby.getLobbyPlayers().clear();
            lobby.endTimer();
            lobbyService.remove(lobbyId);
        }
        getLobbiesList();
    }

    @MessageMapping("/lobby/readyUp")
    public void readyPlayer(Principal principal) {
        Player player = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.get(player.getLobbyId());
        long lobbyId = player.getLobbyId();
        synchronized (lobby) {
            if (lobby.checkPlayerReady(principal.getName())) {
                lobby.removeReadyPlayer(principal.getName());
            } else {
                lobby.addReadyPlayer(principal.getName());
            }
            messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, lobby);
        }
    }

    @MessageMapping("/lobby/chooseCivilization")
    public void chooseCivilization(@Payload Civilization civilization, Principal principal) {
        Player player = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.get(player.getLobbyId());
        synchronized (lobby) {
            if (lobby.checkCivilizationAlreadyChosen(civilization) || lobby.getPlayersReady().contains(player.getPlayerId())) {
                return;
            }
            lobby.setPlayerCivilization(player.getPlayerId(), civilization);
            messagingTemplate.convertAndSend("/topic/lobby/" + player.getLobbyId(), lobby);
        }
    }

    @MessageMapping("/lobby/kickPlayer")
    public void kickPlayer(@Payload String playerId) {
        Player player = playerRepository.get(playerId);
        Lobby lobby = lobbyService.get(player.getLobbyId());
        if (lobby.checkLobbyOwner(playerId)) {
            return;
        }
        synchronized (lobby) {
            lobby.removePlayer(player);
            player.setLobbyId(-1);
        }
        messagingTemplate.convertAndSendToUser(player.getPlayerId(), "/queue/player/currentLobby", "{}");
        messagingTemplate.convertAndSend("/topic/lobby/" + lobby.getLobbyId(), lobby);
    }

    @MessageMapping("/lobbies/getLobbiesList")
    public void getLobbiesList() {
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyService.getList());
    }

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
                            disbandLobby(lobbyId, principal);
                        } else if (lobby.isLobbyReady()) {
                            System.out.println("Lobby is ready to start");
                        }
                    }
                }
            }
        }, 0, 1000);
    }
}
