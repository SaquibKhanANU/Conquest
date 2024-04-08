package com.Game.conquest.engine.board;

import com.Game.conquest.engine.enumTypes.ResourceType;
import lombok.Getter;

import java.util.List;

@Getter
public class Wonder {
    private String name;
    private ResourceType initialResource;
    private List<WonderStage> stages;
    private String image;

    public Wonder(String name, ResourceType initialResource, List<WonderStage> stages, String image) {
        this.name = name;
        this.initialResource = initialResource;
        this.stages = stages;
        this.image = image;
    }
}
