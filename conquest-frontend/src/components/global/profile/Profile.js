import "./Profile.css";
import React from "react";
import { useSelector } from "react-redux";
const Profile = ({ player }) => {
    const currentPlayer = useSelector((state) => state.currentPlayer.player);
    return (
        <div className="profile-container">
            <div className="profile-header">PROFILE</div>
            <div className="profile-body">
                <div className="profile-info">
                    <p className="silver-text">Username:</p>
                    <p>{player.playerName}</p>
                    {currentPlayer &&
                        currentPlayer.playerId === player.playerId && (
                            <p style={{ color: "var(--gold)" }}>(YOU)</p>
                        )}
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
