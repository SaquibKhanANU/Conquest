import Home from "./components/home/Home";
import Navbar from "./components/navbar/Navbar";
import GameBrowser from "./components/game-browser/GameBrowser";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Navbar />
            <Routes>
                <Route>
                    <Route path="/" element={<Home />} />
                    <Route path="gameBrowser" element={<GameBrowser />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
