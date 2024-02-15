import "./Profile.css";
import React from "react";
import { useSelector } from "react-redux";

const Profile = () => {
    const username = useSelector((state) => state.user.username);

    return (
        <div className="profile-container">
            <div>{username}</div>
        </div>
    );
};

export default Profile;
