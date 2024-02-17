import { combineReducers } from "redux";
import lobbyReducer from "./lobbyReducer.ts";

const rootReducer = combineReducers({
    lobby: lobbyReducer,
});

export default rootReducer;
