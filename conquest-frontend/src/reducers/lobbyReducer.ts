import { ADD_LOBBY } from "../actions/actionTypes.js";

const initialState = {
    lobbies: [],
};

const lobbyReducer = (state = initialState, action) => {
    switch (action.type) {
        case ADD_LOBBY:
            return {
                ...state,
                lobbies: [...state.lobbies, action.payload],
            };
        default:
            return state;
    }
};

export default lobbyReducer;
