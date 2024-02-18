import { SET_CURRENT_PLAYER } from "../actions/actionTypes.js";

const initialState = {
    player: {},
};

const currentPlayerReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_CURRENT_PLAYER:
            return {
                ...state,
                player: action.payload,
            };
        default:
            return state;
    }
};

export default currentPlayerReducer;
