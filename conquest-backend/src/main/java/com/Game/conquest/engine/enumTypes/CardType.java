package com.Game.conquest.engine.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CardType {
    RAW_MATERIAL("BROWN"),
    MANUFACTURED_GOOD("GREY"),
    CIVILIAN_STRUCTURE("BLUE"),
    COMMERCIAL_STRUCTURE("YELLOW"),
    MILITARY_STRUCTURE("RED"),
    SCIENCE("GREEN"),
    GUILD("PURPLE");

    @JsonValue
    private final String color;

    CardType(String color) {
        this.color = color;
    }
}
