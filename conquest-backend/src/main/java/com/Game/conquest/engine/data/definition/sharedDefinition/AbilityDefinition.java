package com.Game.conquest.engine.data.definition.sharedDefinition;

import com.Game.conquest.engine.ability.*;
import com.Game.conquest.engine.ability.abilityInterface.Ability;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class AbilityDefinition {
    private int gold;
    private MilitaryAbility military;
    private ScienceAbility science;
    private DiscountAbility discount;
    private NetworkAbility network;
    private ProductionAbility production;
    private PointAbility points;
    private SpecialAbility action;

    public List<Ability> create() {
        List<Ability> abilities = new ArrayList<>();
        Optional.ofNullable(military).ifPresent(abilities::add);
        Optional.ofNullable(science).ifPresent(abilities::add);
        Optional.ofNullable(discount).ifPresent(abilities::add);
        Optional.ofNullable(network).ifPresent(abilities::add);
        Optional.ofNullable(production).ifPresent(abilities::add);
        Optional.ofNullable(points).ifPresent(abilities::add);
        Optional.ofNullable(action).ifPresent(abilities::add);
        return abilities;
    }
}
