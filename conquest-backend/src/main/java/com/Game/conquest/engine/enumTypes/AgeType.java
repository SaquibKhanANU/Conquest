package com.Game.conquest.engine.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AgeType {
    AGE("age"),
    AGE_ONE("age1"),
    AGE_TWO("age2"),
    AGE_THREE("age3");

    @JsonValue
    private final String value;

    AgeType(String value) {
        this.value = value;
    }

    public AgeType getNextAge() {
        int nextOrdinal = this.ordinal() + 1;
        if (nextOrdinal < AgeType.values().length) {
            return AgeType.values()[nextOrdinal];
        } else {
            return null;
        }
    }
}
