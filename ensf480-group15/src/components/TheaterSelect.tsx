import React from 'react';
import '../styles.css';

function TheaterSelect({ theaters, onTheaterSelect }) {
    // Handle theater selection
    const handleTheaterClick = (theater) => {
        onTheaterSelect(theater); // Notify parent of selected theater
    };

    return (
        <section id="theater-selection-section">
            <h2>Select a Theater</h2>
            <div id="theater-list">
                {/* Render each theater */}
                {theaters.map((theater) => (
                    <div
                        key={theater.id}
                        className="theater-item"
                        onClick={() => handleTheaterClick(theater)}
                    >
                        <h3>{theater.name}</h3>
                    </div>
                ))}
            </div>
        </section>
    );
}

export default TheaterSelect;
