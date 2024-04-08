package com.Game.conquest.engine.enumTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ScienceType {
    WHEEL,
    TABLET,
    COMPASS,
    ANY;

    @JsonValue
    public String getValue() {
        return this.name();
    }

    @JsonCreator
    public static ScienceType fromValue(String value) {
        return ScienceType.valueOf(value.toUpperCase());
    }
}