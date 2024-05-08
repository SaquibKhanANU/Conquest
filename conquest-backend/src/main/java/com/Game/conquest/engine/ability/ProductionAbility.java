package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.board.Board;
import com.Game.conquest.engine.board.ResourceStore;
import com.Game.conquest.engine.enumTypes.ResourceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ProductionAbility extends InstantAbility {
    private List<ResourceType> resources;
    @JsonProperty("isSellable")
    private boolean isSellable;

    @JsonSetter
    public void setResources(String resourcesString) {
        this.resources = ResourceType.parseResources(resourcesString);
    }

    @Override
    public void applyAbility(Board board) {
        ResourceStore resourceStore = board.getResourceStore();
        if (this.isSellable) {
            resources.forEach(resourceStore::addPublicResource);
        } else {
            resources.forEach(resourceStore::addPrivateResource);
        }
    }

    @Override
    public void calculatePoints(Board board) {

    }

}
