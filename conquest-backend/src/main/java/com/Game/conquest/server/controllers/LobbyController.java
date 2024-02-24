package com.Game.conquest.server.controllers;

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
            lobby.removePlayer(player);
            player.setLobbyId(-1);
        }
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/player/currentLobby", "{}");
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
            lobbyService.remove(lobbyId);
        }
        messagingTemplate.convertAndSend("/topic/lobbies", lobbyService.getList());
    }

    public boolean checkLobbyOwner(long lobbyId, Principal principal) {
        Lobby lobby = lobbyService.get(lobbyId);
        Player playerPrincipal = playerRepository.get(principal.getName());
        return lobby.getLobbyOwner().getPlayerId().equals(playerPrincipal.getPlayerId());
    }
}
