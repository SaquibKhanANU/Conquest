package com.Game.conquest.server.converter;

import com.Game.conquest.server.dataObjects.LobbyRules;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LobbyRulesConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Private constructor to prevent instantiation
    private LobbyRulesConverter() {
        throw new AssertionError(); // Ensure that this class is never instantiated
    }

    // Convert JSON string to GameDefinition object
    public static LobbyRules fromJson(String json) throws Exception {
        return objectMapper.readValue(json, LobbyRules.class);
    }

    // Convert GameDefinition object to JSON string
    public static String toJson(LobbyRules lobbyRules) throws Exception {
        return objectMapper.writeValueAsString(lobbyRules);
    }
}
