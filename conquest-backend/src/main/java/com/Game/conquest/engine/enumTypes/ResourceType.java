package com.Game.conquest.engine.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ResourceType {
    WOOD('W'),
    STONE('S'),
    ORE('O'),
    CLAY('C'),
    GLASS('G'),
    PAPYRUS('P'),
    LOOM('L'); // Corrected the parameter to a character 'L'

    @JsonValue
    private final char resource;
    ResourceType(char resource) {
        this.resource = resource;
    }

    public static List<ResourceType> parseResources(String resourcesString) {
        List<ResourceType> parsedResources = new ArrayList<>();
        for (char resourceChar : resourcesString.toCharArray()) {
            switch (resourceChar) {
                case 'W':
                    parsedResources.add(ResourceType.WOOD);
                    break;
                case 'S':
                    parsedResources.add(ResourceType.STONE);
                    break;
                case 'O':
                    parsedResources.add(ResourceType.ORE);
                    break;
                case 'C':
                    parsedResources.add(ResourceType.CLAY);
                    break;
                case 'G':
                    parsedResources.add(ResourceType.GLASS);
                    break;
                case 'P':
                    parsedResources.add(ResourceType.PAPYRUS);
                    break;
                case 'L':
                    parsedResources.add(ResourceType.LOOM);
                    break;
                case '/':
                    break;
                default:
                    throw new IllegalArgumentException("Invalid resource type: " + resourceChar);
            }
        }
        return parsedResources;
    }
}
