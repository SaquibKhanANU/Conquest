import { SET_LOBBIES, SET_PLAYERS } from "../actions/actionTypes.js";

const initialState = {
    lobbies: [],
    players: [],
};

const lobbyReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_LOBBIES:
            return {
                ...state,
                lobbies: action.payload,
            };
        case SET_PLAYERS:
            return {
                ...state,
                players: action.payload,
            };
        default:
            return state;
    }
};

export default lobbyReducer;
