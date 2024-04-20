package com.Game.conquest.engine.board;

import com.Game.conquest.engine.Settings;
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
        this.militaryStore = new MilitaryStore();
        this.networkStore = new NetworkStore("test", "test");
    }

    public boolean canPlayCardSelf(Card card) {
        if (card.getCost() == null || card.getCost().getGold() <= this.coins) {
            return true;
        }
        return false;
    }

}
