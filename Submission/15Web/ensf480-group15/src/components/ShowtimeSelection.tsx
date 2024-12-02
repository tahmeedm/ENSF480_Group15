import React from 'react'

function ShowtimeSelection({ Showtimes, selectShowtime, selectedShowtime }) {

    const handleShowtimeClick = (showtime) => {
        // Update the selected showtime in the parent component
        console.log(showtime);
        selectShowtime(showtime);
    }

    return (
        <section id="showtime-selection-section">
            <h2>Select a Showtime</h2>
            <div id="showtime-list">
                {Showtimes.map((showtime) => (
                    <div key={showtime.id}
                        className={showtime === selectedShowtime ? "showtime-item-selected" : "showtime-item"}
                        onClick={() => handleShowtimeClick(showtime)}
                    >
                        <h3>{showtime}</h3>
                    </div>
                ))}
            </div>
        </section>
    )
}

export default ShowtimeSelection