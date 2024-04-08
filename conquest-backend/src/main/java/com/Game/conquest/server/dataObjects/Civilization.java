package com.Game.conquest.server.dataObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Civilization {
    private String name;
    private String color;
    private String side;

    public Civilization(String name, String color, String side) {
        this.name = name;
        this.color = color;
        this.side = side;
    }

    public boolean checkSideChosen(String newSide) {
        return Objects.equals(side, newSide);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Civilization that = (Civilization) obj;
        return Objects.equals(name, that.name);
    }
}
