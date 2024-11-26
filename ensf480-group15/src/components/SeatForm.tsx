import React, { useState, useEffect } from 'react';

function SeatForm({ onSeatSelect, SEAT_COST }) {
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);

    // Generate 100 seats
    const generateSeats = () => {
        const seats = [];
        for (let i = 0; i < 100; i++) {
            seats.push({
                id: i + 1, // Seat ID (1-based index)
                selected: false, // Initially not selected
            });
        }
        return seats;
    };

    const [seats, setSeats] = useState(generateSeats());

    // Update total price based on selected seats
    const updateTotalPrice = () => {
        console.log(selectedSeats);
        setTotalPrice(selectedSeats.length * SEAT_COST);
    };

    // Handle seat click
    const handleSeatClick = (seatId) => {
        const newSelectedSeats = [...selectedSeats];
        if (newSelectedSeats.includes(seatId)) {
            // If seat is already selected, deselect it
            const index = newSelectedSeats.indexOf(seatId);
            newSelectedSeats.splice(index, 1);
        } else {
            // Otherwise, select the seat
            newSelectedSeats.push(seatId);
        }
        setSelectedSeats(newSelectedSeats);
    };

    // Trigger onSeatSelect when selection is confirmed
    const handleConfirmSelection = () => {
        onSeatSelect(selectedSeats);
    };

    useEffect(() => {
        // Reset selected seats and price if seats change
        setSelectedSeats([]);
        setTotalPrice(0);
    }, []);

    useEffect(() => {
        updateTotalPrice();
    }, [selectedSeats]);

    return (
        <section id="seat-selection-section">
            <h2>Select Your Seats</h2>
            <div id="seat-map">
                {seats.map((seat) => (
                    <div
                        key={seat.id}
                        className={`seat ${selectedSeats.includes(seat.id) ? 'selected' : ''}`}
                        onClick={() => handleSeatClick(seat.id)}
                    >
                        {seat.id}
                    </div>
                ))}
            </div>
            <p id="total-price">Total Price: ${totalPrice}</p>
            <button
                id="confirm-selection-button"
                onClick={handleConfirmSelection}
                disabled={selectedSeats.length === 0} // Disable if no seats selected
            >
                Confirm Selection
            </button>
        </section>
    );
}

export default SeatForm;
