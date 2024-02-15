import "./Home.css";
import React from "react";
import ChooseNameForm from "./ChooseNameForm";
import BackgroundImage from "../../resources/imgs/game_browser_background_img.png";

const Home = () => {
    return (
        <div
            className="home-container"
            style={{ backgroundImage: `url(${BackgroundImage})` }}
        >
            <div className="home-body">
                <ChooseNameForm />
            </div>
        </div>
    );
};

export default Home;
