package com.Game.conquest.engine.enumTypes;

public enum WonderSide {
    A,
    B;

    public static WonderSide fromString(String sideString) {
        return valueOf(sideString);
    }
}
