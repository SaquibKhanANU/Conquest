package com.Game.conquest.server.controllers;


import com.Game.conquest.server.dataObjects.Game;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import com.Game.conquest.server.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameBrowserController gameBrowserController;

    @MessageMapping("/game/LeaveGame")
    public void leaveGame(long gameId, Principal principal) {
        Player player = playerRepository.get(principal.getName());
        Game game = gameService.get(gameId);
        synchronized (game) {
            player.setGameId(-1);
            game.removePlayer(player);
            messagingTemplate.convertAndSendToUser(player.getPlayerId(), "/queue/player/currentGame", "{}");
            if (game.getGamePlayers().isEmpty()) {
                disbandGame(gameId);
                return;
            }
        }
        messagingTemplate.convertAndSend("/topic/lobby/" + gameId, game);
        gameBrowserController.getGamesList();
    }

    @MessageMapping("/game/DisbandGame")
    public void disbandGame(long gameId) {
        Game game = gameService.get(gameId);
        synchronized (game) {
            game.getGamePlayers().forEach(player -> {
                player.setGameId(-1);
                messagingTemplate.convertAndSendToUser(player.getPlayerId(), "/queue/player/currentGame", "{}");
            });
            gameService.remove(gameId);
        }
        gameBrowserController.getGamesList();
    }


    @MessageMapping("/game/arrangeBoards")
    private void arrangeBoards(long gameId, Principal principal) {
        Game game = gameService.get(gameId);

    }
}
