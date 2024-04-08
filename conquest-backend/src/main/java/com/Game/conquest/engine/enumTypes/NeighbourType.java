package com.Game.conquest.engine.enumTypes;

import lombok.Getter;

@Getter
public enum NeighbourType {
    RIGHT("RIGHT"),
    LEFT("LEFT"),
    SELF("SELF");

    private final String neighbour;

    NeighbourType(String neighbour) {
        this.neighbour = null;
    }
}

