package com.Game.conquest.server.dataObjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LobbyEvent {
    private String eventType;
    private Object eventData;

    public LobbyEvent(String eventType, Object eventData) {
        this.eventType = eventType;
        this.eventData = eventData;
    }

    public String toJson() {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            // Convert object to JSON string
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }
}
