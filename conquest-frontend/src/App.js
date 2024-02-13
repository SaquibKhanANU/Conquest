import Home from "./ui/components/home/Home";
import Navbar from "./ui/components/navbar/Navbar";

function App() {
    return (
        <div className="App">
            <div>
                <Navbar />
            </div>
            <div>
                <Home />
            </div>
        </div>
    );
}

export default App;
