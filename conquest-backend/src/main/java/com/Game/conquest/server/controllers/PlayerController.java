package com.Game.conquest.server.controllers;


import com.Game.conquest.server.dataObjects.Player;
import com.Game.conquest.server.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public PlayerController(PlayerRepository playerRepository, SimpMessagingTemplate messagingTemplate) {
        this.playerRepository = playerRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/addPlayer")
    public void addPlayer(@Payload String username) {
        Player player = new Player(username);
        playerRepository.save(player);
    }

    @MessageMapping("/deletePlayer")
    public void deletePlayer(@Payload Long id) {
        playerRepository.deleteById(id);
    }
    

    @MessageMapping("/getPlayers")
    public void getPlayerList() {
        messagingTemplate.convertAndSend("/topic/players", playerRepository.findAll());
    }
}
