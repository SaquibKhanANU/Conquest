package com.Game.conquest.engine.data.definition.wonderDefinition;

import com.Game.conquest.engine.board.Wonder;
import com.Game.conquest.engine.enumTypes.WonderSide;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Getter
public class WonderDefinition {
    private String name;
    private Map<WonderSide, WonderSideDefinition> sides;

    public Wonder create(WonderSide wonderSide) {
        return sides.get(wonderSide).create(name);
    }
}