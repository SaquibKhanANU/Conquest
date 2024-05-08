import { combineReducers } from "redux";
import lobbyReducer from "./lobbyReducer.ts";
import currentPlayerReducer from "./CurrentPlayerReducer.ts";
import gameActionReducer from "./gameActionReducer.ts";

const rootReducer = combineReducers({
    lobby: lobbyReducer,
    currentPlayer: currentPlayerReducer,
    gameAction: gameActionReducer
});

export default rootReducer;
