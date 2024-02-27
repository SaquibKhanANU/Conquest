package com.Game.conquest.server.controllers;

import com.Game.conquest.server.dataObjects.Civilization;
import com.Game.conquest.server.dataObjects.Lobby;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import com.Game.conquest.server.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class LobbyController {

    @Autowired
    private LobbyService lobbyService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayerRepository playerRepository;

    @MessageMapping("/lobby/leaveLobby")
    public void leaveLobby(@Payload long lobbyId, Principal principal) {
        Player player = playerRepository.get(principal.getName());
        Lobby lobby = lobbyService.get(lobbyId);
        synchronized (lobby) {
            if (lobby.getLobbyPlayers().isEmpty() || checkLobbyOwner(lobbyId, principal)) {
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
        if (!checkLobbyOwner(lobbyId, principal)) {
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
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyService.getList());
    }

    @MessageMapping("/lobby/readyUp")
    public void readyPlayer(@Payload long lobbyId, Principal principal) {
        Lobby lobby = lobbyService.get(lobbyId);
        synchronized (lobby) {
            if (checkPlayerAlreadyReady(lobbyId, principal)) {
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



    // TODO move this checks to a appropriate class

    public boolean checkLobbyOwner(long lobbyId, Principal principal) {
        Lobby lobby = lobbyService.get(lobbyId);
        Player playerPrincipal = playerRepository.get(principal.getName());
        return lobby.getLobbyOwner().getPlayerId().equals(playerPrincipal.getPlayerId());
    }

    public boolean checkPlayerAlreadyReady(long lobbyId, Principal principal) {
        Lobby lobby = lobbyService.get(lobbyId);
        return lobby.getPlayersReady().contains(principal.getName());
    }
}
