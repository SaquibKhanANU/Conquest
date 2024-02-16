import * as ActionType from "./actionTypes.js";

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
}