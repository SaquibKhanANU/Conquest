import "./index.css";
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { Provider } from "react-redux";
import store from "./redux/store/store.ts";
import { SessionProvider } from "./components/contexts/SessionContext.js";

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
