import { REQUEST_CHOOSE_USERNAME } from "../actions/actionTypes.js";

const initialState = {
    username: null,
    connected: false,
};

const userReducer = (state = initialState, action) => {
    switch (action.type) {
        case REQUEST_CHOOSE_USERNAME:
            return {
                ...state,
                username: action.payload.username,
                connected: true,
            };
        default:
            return state;
    }
};

export default userReducer;
