package com.Game.conquest.engine.ability.abilityInterface;

import com.Game.conquest.engine.ability.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DiscountAbility.class, name = "discount"),
        @JsonSubTypes.Type(value = GoldAbility.class, name = "gold"),
        @JsonSubTypes.Type(value = MilitaryAbility.class, name = "military"),
        @JsonSubTypes.Type(value = ProductionAbility.class, name = "production"),
        @JsonSubTypes.Type(value = NetworkAbility.class, name = "network"),
        @JsonSubTypes.Type(value = ScienceAbility.class, name = "science"),
        @JsonSubTypes.Type(value = SpecialAbility.class, name = "trade"),
        @JsonSubTypes.Type(value = PointAbility.class, name = "point"),
})
public abstract class InstantAbility implements Ability {
}
