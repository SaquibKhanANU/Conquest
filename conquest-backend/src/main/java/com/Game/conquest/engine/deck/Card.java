package com.Game.conquest.engine.deck;

import com.Game.conquest.engine.ability.abilityInterface.Ability;
import com.Game.conquest.engine.common.Cost;
import com.Game.conquest.engine.enumTypes.CardType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {
    private String name;
    private CardType cardType;
    private Cost cost;
    private List<Ability> abilities;
    private List<String> children;
    private List<String> parents;
    private String image;
    private String backImage;
}
