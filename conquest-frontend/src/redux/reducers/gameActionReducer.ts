import { SET_SELECTED_CARD, SET_PREPARED_ACTION } from "../actions/actionTypes";

const initialState = {
    selectedCard: null,
    selectedAction: null,
};

const gameActionReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_SELECTED_CARD:
            return {
                ...state,
                selectedCard: action.payload,
            };
        case SET_PREPARED_ACTION:
            return {
                ...state,
                selectedAction: action.payload,
            };
        default:
            return state;
    }
};


export default gameActionReducer;