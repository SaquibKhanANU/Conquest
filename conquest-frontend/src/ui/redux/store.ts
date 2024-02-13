import { configureStore } from '@reduxjs/toolkit';
import rootReducer from "./reducers.ts";

const store = configureStore({
    reducer: rootReducer,
    // Other options like middleware, devTools, etc. can be added here
  });

export default store;
