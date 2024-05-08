package com.Game.conquest.engine.ability.abilityInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class EndAbility implements Ability {
    // TODO: Any ability that isnt instant e.g. at the end of a age or the whole game add it to a special list that
    // on boolean check during the game loop, it will loop through these special abilities and apply them,
    // make sure  each ability has the players id assigned as a key.
}
