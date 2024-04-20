import './ThreeWayArrow.css';
const ThreeWayArrow = () => {
    const handleCardClick = (direction) => {
        console.log(direction);
    };
    return (
        <div className='three-way-arrow-container'>
            <div className="arrow-container">
                <div className="card-cost">
                    <p>2</p>
                    <img
                        src={process.env.PUBLIC_URL + "/imgs/tokens/coin.png"}
                        alt="coins"
                    />
                </div>
                <p
                    className="arrow arrow-left"
                    onClick={() => handleCardClick("LEFT")}
                >
                    &#10144;
                </p>
            </div>
            <div className="arrow-container arrow-container-bottom">
                <p
                    className="arrow arrow-bottom"
                    onClick={() => handleCardClick("SELF")}
                >
                    &#10144;
                </p>
                <div className="card-cost">
                    <p>1</p>
                    <img
                        src={process.env.PUBLIC_URL + "/imgs/tokens/coin.png"}
                        alt="coins"
                    />
                </div>
            </div>
            <div className="arrow-container ">
                <p
                    className="arrow arrow-right"
                    onClick={() => handleCardClick("RIGHT")}
                >
                    &#10144;
                </p>
                <div className="card-cost">
                    <p>2</p>
                    <img
                        src={process.env.PUBLIC_URL + "/imgs/tokens/coin.png"}
                        alt="coins"
                    />
                </div>
            </div>

        </div>
    );
};

export default ThreeWayArrow;
