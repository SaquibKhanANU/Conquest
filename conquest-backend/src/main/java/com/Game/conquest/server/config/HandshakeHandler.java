package com.Game.conquest.server.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.Map;

public class HandshakeHandler extends DefaultHandshakeHandler {
    private int counter = 0;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Principal p = super.determineUser(request, wsHandler, attributes);
        if (p == null) {
            p = new UsernamePasswordAuthenticationToken("player" + counter++, null);
        }
        return p;
    }
}
