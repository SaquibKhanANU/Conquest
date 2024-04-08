package com.Game.conquest.engine.common;

import com.Game.conquest.engine.enumTypes.ResourceType;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class Cost {
    private int gold;
    private List<ResourceType> resources;

    @JsonSetter
    public void setResources(String resourcesString) {
        this.resources = ResourceType.parseResources(resourcesString);
    }
}
