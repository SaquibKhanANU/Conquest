import {
    REQUEST_CHOOSE_USERNAME,
    CHOOSE_USERNAME_SUCCESS,
    CHOOSE_USERNAME_FAILURE,
} from "./actions.ts";

const initialState = {
    username: null,
    loading: false,
    error: null,
};

const userReducer = (state = initialState, action) => {
    switch (action.type) {
        case REQUEST_CHOOSE_USERNAME:
            return {
                ...state,
                loading: true,
            };
        case CHOOSE_USERNAME_SUCCESS:
            return {
                ...state,
                loading: false,
                username: action.payload.username,
            };
        case CHOOSE_USERNAME_FAILURE:
            return {
                ...state,
                loading: false,
                error: action.payload.error,
            };
        default:
            return state;
    }
};

export default userReducer;
