package com.Game.conquest.engine.data.definition;

import com.Game.conquest.server.dataObjects.Civilization;
import com.Game.conquest.engine.Game;
import com.Game.conquest.engine.board.Board;
import com.Game.conquest.engine.board.BoardManager;
import com.Game.conquest.engine.board.Wonder;
import com.Game.conquest.engine.data.definition.deckDefinition.DeckDefinition;
import com.Game.conquest.engine.data.definition.wonderDefinition.WondersDefinition;
import com.Game.conquest.engine.deck.Deck;
import com.Game.conquest.engine.deck.DeckManager;
import com.Game.conquest.engine.util.JsonConverter;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
public class GameDefinition {
    private final DeckDefinition deck;
    private final WondersDefinition wonders;

    public GameDefinition() throws IOException {
        String path = "src/main/java/com/Game/conquest/engine/data/json/";
        this.deck = JsonConverter.fromJson(path + "cards.json", DeckDefinition.class);
        this.wonders = JsonConverter.fromJson(path + "wonders.json", WondersDefinition.class);
    }

    public Deck prepareDeck(int numPlayers) {
       return deck.create(numPlayers);
    }

    public Map<String, Board> preparePlayerBoard(Map<String, Civilization> playersBoard) {
        Map<String, Board> preparedBoards = new HashMap<>();
        for (Map.Entry<String, Civilization> entry : playersBoard.entrySet()) {
            String player = entry.getKey();
            Civilization civilization = entry.getValue();
            Wonder wonder = wonders.getWonder(civilization.getName(), civilization.getSide());
            Board board = new Board(wonder); // Assuming Board constructor requires a Wonder
            preparedBoards.put(player, board);
        }
        return preparedBoards;
    }

    public Game createGame(Map<String, Civilization> playersBoard) {
        BoardManager boardManager = new BoardManager(preparePlayerBoard(playersBoard));
        DeckManager deckManager = new DeckManager(prepareDeck(playersBoard.size()), playersBoard.keySet());
        return new Game(deckManager, boardManager);
    }
}



