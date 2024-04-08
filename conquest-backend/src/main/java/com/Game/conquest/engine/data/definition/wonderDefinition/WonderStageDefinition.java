package com.Game.conquest.engine.data.definition.wonderDefinition;

import com.Game.conquest.engine.board.WonderStage;
import com.Game.conquest.engine.common.Cost;
import com.Game.conquest.engine.data.definition.sharedDefinition.AbilityDefinition;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WonderStageDefinition {
    private Cost cost;
    private AbilityDefinition ability;

    protected WonderStage create() {
        return new WonderStage(cost, ability.create());
    }
}
