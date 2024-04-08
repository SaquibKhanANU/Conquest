package com.Game.conquest.engine.data.definition.wonderDefinition;

import com.Game.conquest.engine.board.Wonder;
import com.Game.conquest.engine.enumTypes.ResourceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class WonderSideDefinition {
    private ResourceType initialResource;
    private List<WonderStageDefinition> stages;
    private String image;

    public Wonder create(String name) {
        return new Wonder(name, initialResource, stages.stream().map(WonderStageDefinition::create).toList(), image);
    }

}
