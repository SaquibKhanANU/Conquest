package com.Game.conquest.server.controllers;


import com.Game.conquest.engine.common.Action;
import com.Game.conquest.server.converter.GenericConverter;
import com.Game.conquest.server.dataObjects.Game;
import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import com.Game.conquest.server.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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


    @MessageMapping("/game/receiveGameAction")
    public void receiveGameAction(@Payload String action, Principal principal) throws Exception {
        System.out.println("Received action from player: " + principal.getName() + " action: " + action);
        Action actionParsed =  GenericConverter.fromJson(action, Action.class);
        System.out.println("Received action from player: " + principal.getName() + " action: " + actionParsed);
        Player player = playerRepository.get(principal.getName());
        com.Game.conquest.engine.Game game = gameService.get(player.getGameId()).getGame();
        synchronized (game) {
            game.receiveAction(actionParsed, player.getPlayerId());
        }
        messagingTemplate.convertAndSend("/topic/game/" + player.getGameId(), game);
    }
}
