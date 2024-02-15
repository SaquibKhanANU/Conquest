// SessionContext.js
import React, { createContext, useContext, useState } from "react";

const SessionContext = createContext();

export const SessionProvider = ({ children }) => {
    const [session, setSession] = useState(null);

    const updateSession = (newSession) => {
        setSession(newSession);
    };

    return (
        <SessionContext.Provider value={{ session, updateSession }}>
            {children}
        </SessionContext.Provider>
    );
};

export const useSession = () => useContext(SessionContext);
