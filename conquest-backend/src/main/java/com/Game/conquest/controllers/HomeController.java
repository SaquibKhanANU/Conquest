package com.Game.conquest.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @MessageMapping("chooseName")
    public void chooseName(String name) {
        System.out.println("Name: " + name);
    }
}
