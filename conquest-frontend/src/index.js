import "./index.css";
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { Provider } from "react-redux";
import store from "./store/store.ts";
import { SessionProvider } from "./components/session/SessionContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <SessionProvider>
        <Provider store={store}>
            <React.StrictMode>
                <App />
            </React.StrictMode>
        </Provider>
    </SessionProvider>
);
