import * as ActionType from "./actionTypes.js";

export const requestChooseUsername = (username) => ({
    type: ActionType.REQUEST_CHOOSE_USERNAME,
    payload: { username },
});

export const addLobby = (lobbyData) => {
    return {
        type: ActionType.ADD_LOBBY,
        payload: lobbyData,
    };
};
