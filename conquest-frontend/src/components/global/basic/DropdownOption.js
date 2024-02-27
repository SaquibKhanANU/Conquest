import React from "react";

const DropdownOption = ({
    label,
    currentValue,
    setValue,
    options,
}) => {
    return (
        <div className="create-lobby-option">
            <p>{label}</p>
            <div className="dropdown">
                <button className="dropbtn" type="button">
                    {currentValue}
                </button>
                <div className="dropdown-content">
                    {options.map((option, index) => (
                        <p key={index} onClick={() => setValue(option)}>
                            {option}
                        </p>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default DropdownOption;
