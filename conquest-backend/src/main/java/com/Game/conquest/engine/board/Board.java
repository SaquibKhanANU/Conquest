package com.Game.conquest.engine.board;

import com.Game.conquest.engine.Settings;
import com.Game.conquest.engine.ability.abilityInterface.Ability;
import com.Game.conquest.engine.common.Cost;
import com.Game.conquest.engine.deck.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Board {
    private Wonder wonder;
    private int totalPoints;
    private int coins;
    private List<Card> playedCards;
    @JsonIgnore
    private ResourceStore resourceStore;
    @JsonIgnore
    private ScienceStore scienceStore;
    @JsonIgnore
    private MilitaryStore militaryStore;
    @JsonIgnore
    private NetworkStore networkStore;

    public Board(Wonder wonder) {
        this.wonder = wonder;
        this.totalPoints = 0;
        this.coins = Settings.getInitialCoins();
        this.playedCards = new ArrayList<>();
        this.resourceStore = new ResourceStore(wonder.getInitialResource());
        this.scienceStore = new ScienceStore();
        this.militaryStore = new MilitaryStore(Settings.getPointsPerMilitaryVictory());
        this.networkStore = new NetworkStore("test", "test");
    }

    public boolean canPlayCardSelf(Card card) {
        /* TODO: The following checks
            1. Enough resources in the ResourceStore
            2. Enough coins in board coins//
            3. If it is free e.g. no resource cost
            4. If it is free e.g. a child structure is built
         */
        boolean resourceCheck = false;
        boolean goldCheck = false;
        boolean freeCheckCost = false;
        Cost cost = card.getCost();
        if (cost != null) {
            resourceCheck = this.resourceStore.checkCardPlayabilityResource(card);
            goldCheck = card.getCost().getGold() <= this.coins;
        } else {
            freeCheckCost = true;
        }
        boolean freeCheckChildBuilt = playedCards.stream()
                .anyMatch(builtCard -> {
                    List<String> children = builtCard.getChildren();
                    return children != null && children.contains(card.getParents());
                });
        return (resourceCheck && goldCheck) || freeCheckCost || freeCheckChildBuilt;
    }

    public void playCard(Card card) {
        this.playedCards.add(card);
        for (Ability ability : card.getAbilities()) {
            ability.applyAbility(this);
            ability.calculatePoints(this);
        }
    }

    public void buildWonder(Card card) {

    }

    public void discardCard(Card card) {
        this.coins += 3;
    }
    public void changeCoins(int numCoins) {
        this.coins += numCoins;
    }
    public void addPoints(int points) {
        this.totalPoints += points;
    }

}
