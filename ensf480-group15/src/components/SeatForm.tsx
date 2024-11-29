import React, { useState, useEffect } from 'react';
import axios from 'axios';

function SeatForm({ onSeatSelect, theaterId, globalSeats, seatingArrangement }) {
    // State hooks
    const [selectedSeats, setSelectedSeats] = useState([]);  // Selected seats state
    const [totalPrice, setTotalPrice] = useState(0);  // Total price state

    // Function to load seats from API
    const loadSeats = () => {
        console.log('Fetching seats for theaterId:', theaterId);
        axios.get('http://localhost:8083/fetchSeats', {
            params: { args: theaterId }
        })
            .then((response) => {
                if (response && response.data) {
                    console.log('Response data:', response.data);
                    setSeats(response.data);  // Set the fetched seats
                } else {
                    console.error('Error fetching seats: no response or invalid response');
                }
            })
            .catch((error) => {
                console.error('Error fetching seats:', error);
            });
    };

    // Update total price based on selected seats
    const updateTotalPrice = () => {
        let totalCost = 0;
        for (let i = 0; i < selectedSeats.length; i++) {
            totalCost += selectedSeats[i].price;  // Assuming each seat has a 'price' property
        }
        setTotalPrice(totalCost);
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
        setSelectedSeats(newSelectedSeats);  // Update selected seats state
    };

    // Trigger onSeatSelect when selection is confirmed
    const handleConfirmSelection = () => {
        onSeatSelect(selectedSeats);  // Pass the selected seats to the parent component
    };

    // useEffect to fetch seats only once when the component mounts
    useEffect(() => {
        if (theaterId) {
            loadSeats();  // Only fetch seats when theaterId is available
        }
    }, [theaterId]);  // Run once when component mounts or when theaterId changes

    // useEffect to update total price whenever selectedSeats change
    useEffect(() => {
        updateTotalPrice();
    }, [selectedSeats]);  // Update total price when selectedSeats changes

    return (
        <section id="seat-selection-section">
            <h2>Select Your Seats</h2>
            <div id="seat-map">
                {seatingArrangement.map((seat) => (
                    <div
                        key={seat.seatNumber}
                        className={`seat ${selectedSeats.includes(seat.seatNumber) ? 'selected' : ''}`}
                        onClick={() => handleSeatClick(seat.seatNumber)}
                    >
                        {seat.seatNumber}
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
