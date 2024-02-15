import { combineReducers } from "redux";
import userReducer from "./userReducer.ts";
import lobbyReducer from "./lobbyReducer.ts";

const rootReducer = combineReducers({
    user: userReducer,
    lobby: lobbyReducer,
});

export default rootReducer;
