package com.Game.conquest.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chooseName")
    @SendTo("/topic/public")
    public String sendMessage(@Payload String message) {
        System.out.println("Message: " + message);
        return message;
    }
}
