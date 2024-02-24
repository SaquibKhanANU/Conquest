package com.Game.conquest.server.dataObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Getter
@Setter
@NoArgsConstructor
public class Lobby {
    private long lobbyId;
    private Player lobbyOwner;
    private LobbyRules lobbyRules;
    private ArrayList<Player> lobbyPlayers;
    private ArrayList<String> playersReady;
    private Map<String, Civilization> playerCivilizations;
    public Lobby(long lobbyId, Player lobbyOwner, LobbyRules lobbyRules) {
        this.lobbyId = lobbyId;
        this.lobbyOwner = lobbyOwner;
        this.lobbyRules = lobbyRules;
        this.lobbyPlayers = new ArrayList<>(lobbyRules.getMaxPlayers());
        this.playersReady = new ArrayList<>(lobbyRules.getMaxPlayers());
        this.playerCivilizations = new ConcurrentHashMap<>(lobbyRules.getMaxPlayers());
    }

    @Synchronized
    public void addPlayer(Player player) {
        lobbyPlayers.add(player);
        setPlayerRandomCivilization(player.getPlayerId());
    }

    @Synchronized
    public void removePlayer(Player player) {
        lobbyPlayers.remove(player);
        playersReady.remove(player.getPlayerId());
        playerCivilizations.remove(player.getPlayerId());
    }

    @Synchronized
    public ArrayList<Player> getLobbyPlayers() {
        return lobbyPlayers;
    }

    @Synchronized
    public boolean isFull() {
        return lobbyPlayers.size() == lobbyRules.getMaxPlayers();
    }

    @Synchronized
    public boolean isLobbyReady() {
        return playersReady.size() == lobbyRules.getMaxPlayers();
    }

    @Synchronized
    public void addReadyPlayer(String player) {
        playersReady.add(player);
    }
    @Synchronized
    public void removeReadyPlayer(String player) {
        playersReady.remove(player);
    }

    @Synchronized
    public void setPlayerCivilization(String playerId, Civilization civilization) {
        playerCivilizations.put(playerId, civilization);
    }

    @Synchronized
    public Civilization getPlayerCivilization(String playerId) {
        return playerCivilizations.get(playerId);
    }

    @Synchronized
    public void setPlayerRandomCivilization(String playerId) {
        ArrayList<Civilization> civilizations = new ArrayList<>(lobbyRules.getCivilizations());
        int randomIndex = (int) (Math.random() * civilizations.size());
        Civilization randomCivilization = civilizations.get(randomIndex);
        while (checkCivilizationAlreadyChosen(randomCivilization)) {
            randomIndex = (int) (Math.random() * civilizations.size());
            randomCivilization = civilizations.get(randomIndex);
        }
        playerCivilizations.put(playerId, randomCivilization);
    }

    @Synchronized
    public boolean checkCivilizationAlreadyChosen(Civilization civilization) {
        return playerCivilizations.containsValue(civilization);
    }
}
