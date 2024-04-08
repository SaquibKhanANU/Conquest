package com.Game.conquest.engine.ability;

import com.Game.conquest.engine.ability.abilityInterface.InstantAbility;
import com.Game.conquest.engine.enumTypes.NeighbourType;
import com.Game.conquest.engine.enumTypes.ResourceType;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class DiscountAbility extends InstantAbility {
    private List<ResourceType> resourceTypes;
    private List<NeighbourType> providers;
    private int discountedPrice;

    @JsonSetter
    public void setResourceTypes(String resourceTypesString) {
        this.resourceTypes = ResourceType.parseResources(resourceTypesString);
    }
}
