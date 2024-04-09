import * as ActionType from "./actionTypes.js";

// -- API ACTIONS TYPES

// -- ACTIONS TYPES
export const setCurrentPlayer = (playerData) => {
    return {
        type: ActionType.SET_CURRENT_PLAYER,
        payload: playerData,
    };
};

export const setCurrentLobby = (lobbyData) => {
    return {
        type: ActionType.SET_CURRENT_LOBBY,
        payload: lobbyData,
    };
};

export const setCurrentGame = (gameData) => {
    return {
        type: ActionType.SET_CURRENT_GAME,
        payload: gameData,
    };
};

export const setLobbies = (lobbiesData) => {
    return {
        type: ActionType.SET_LOBBIES,
        payload: lobbiesData,
    };
};

export const setPlayers = (playersData) => {
    return {
        type: ActionType.SET_PLAYERS,
        payload: playersData,
    };
};

export const setGames = (gamesData) => {
    return {
        type: ActionType.SET_GAMES,
        payload: gamesData,
    };
};

export const setSelectedCard = (cardData) => {
    return {
        type: ActionType.SET_SELECTED_CARD,
        payload: cardData,
    };
};

export const setSelectedAction = (actionData) => {
    return {
        type: ActionType.SET_PREPARED_ACTION,
        payload: actionData,
    };
};
