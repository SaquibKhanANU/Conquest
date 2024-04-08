package com.Game.conquest.engine.data.definition.wonderDefinition;

import com.Game.conquest.engine.board.Wonder;
import com.Game.conquest.engine.enumTypes.WonderSide;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class WondersDefinition {
    private List<WonderDefinition> wonders;

    public Wonder getWonder(String name, String side) {
        return wonders.stream().filter(wonder -> wonder.getName().equals(name)).findFirst().get().create(WonderSide.valueOf(side));
    }
}
