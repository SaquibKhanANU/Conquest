import "./Profile.css";
import React from "react";
import { useSelector } from "react-redux";

const Profile = () => {
    const currentPlayer = useSelector((state) => state.currentPlayer.player);

    return (
        <div className="profile-container">
            <div className="profile-header">PROFILE</div>
            <div className="profile-body">
                <div className="profile-info">
                    <p>Username:</p>
                    <p>{currentPlayer.playerName}</p>
                </div>
                <div className="profile-info">
                    <p>Games played:</p>
                    <p>0</p>
                </div>
                <div className="profile-info">
                    <p>Games won:</p>
                    <p>0</p>
                </div>
            </div>
        </div>
    );
};

export default Profile;
