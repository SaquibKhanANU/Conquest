import {
    SET_CURRENT_PLAYER,
    SET_CURRENT_LOBBY,
    SET_CURRENT_GAME,
} from "../actions/actionTypes.js";

const initialState = {
    player: {},
    currentLobby: {},
    currentGame: {},
};

const currentPlayerReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_CURRENT_PLAYER:
            return {
                ...state,
                player: action.payload,
            };
        case SET_CURRENT_LOBBY:
            return {
                ...state,
                currentLobby: action.payload,
            };
        case SET_CURRENT_GAME:
            return {
                ...state,
                currentGame: action.payload,
            };
        default:
            return state;
    }
};

export default currentPlayerReducer;
