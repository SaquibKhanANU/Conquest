import * as ActionType from "./actionTypes.js";

export const requestChooseUsername = (username) => ({
    type: ActionType.REQUEST_CHOOSE_USERNAME,
    payload: { username },
});

