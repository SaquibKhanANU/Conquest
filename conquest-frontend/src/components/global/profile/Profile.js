import "./Profile.css";
import React from "react";

const Profile = ({ player }) => {
    return (
        <div className="profile-container">
            <div className="profile-header">PROFILE</div>
            <div className="profile-body">
                <div className="profile-info">
                    <p className="silver-text">Username:</p>
                    <p>{player.playerName}</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">Games played:</p>
                    <p>0</p>
                </div>
                <div className="profile-info">
                    <p className="silver-text">Games won:</p>
                    <p>0</p>
                </div>
            </div>
        </div>
    );
};

export default Profile;
