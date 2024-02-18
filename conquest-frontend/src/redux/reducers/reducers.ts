import { combineReducers } from "redux";
import lobbyReducer from "./lobbyReducer.ts";
import currentPlayerReducer from "./CurrentPlayerReducer.ts";

const rootReducer = combineReducers({
    lobby: lobbyReducer,
    currentPlayer: currentPlayerReducer,
});

export default rootReducer;
