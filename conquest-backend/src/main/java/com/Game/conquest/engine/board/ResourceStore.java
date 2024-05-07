package com.Game.conquest.engine.board;

import com.Game.conquest.engine.deck.Card;
import com.Game.conquest.engine.enumTypes.ResourceType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResourceStore {
    private ResourceType initialResource;
    private List<ResourceType> publicResources;
    private List<ResourceType> privateResources;
    public ResourceStore(ResourceType initialResource) {
        this.initialResource = initialResource;
        this.publicResources = new ArrayList<>();
        this.privateResources = new ArrayList<>();
        this.publicResources.add(initialResource);
    }
    public void addPublicResource(ResourceType resourceType) {
        this.publicResources.add(resourceType);
    }

    public void addPrivateResource(ResourceType resourceType) {
        this.privateResources.add(resourceType);
    }

    public boolean checkCardPlayabilityResource(Card card) {
        List<ResourceType> resourceCost = card.getCost().getResources();
        if (resourceCost == null) {
            return true;
        }
        for (ResourceType resource : resourceCost) {
            if (!publicResources.contains(resource) && !privateResources.contains(resource)) {
                return false;
            }
        }
        return true;
    }

}
