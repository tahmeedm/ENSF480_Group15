import React, { useState, useEffect } from 'react';
import axios from 'axios';

function SeatForm({
    onSeatSelect,
    setGlobalSeats,
    seatingArrangement,
    priceHandler,
    selectedScreening
}) {
    // State hooks
    const [selectedSeats, setSelectedSeats] = useState([]);  // Selected seats state
    const [totalPrice, setTotalPrice] = useState(0);  // Total price state

    // Function to load seats from API
    const loadSeats = () => {
        console.log('Fetching seats for selectedScreening:', selectedScreening);
        axios.post('http://localhost:8083/fetchSeats', selectedScreening)
            .then((response) => {
                if (response && response.data) {
                    console.log('Response data:', response.data);
                    setGlobalSeats(response.data);  // Set the fetched seats
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
        priceHandler(totalCost);
        setTotalPrice(totalCost);
    };

    // Handle seat click (store full seat objects instead of just seat numbers)
    const handleSeatClick = (seatNumber) => {
        const seat = seatingArrangement.find((seat) => seat.seatNumber === seatNumber);  // Find the full seat object by seatNumber
        const newSelectedSeats = [...selectedSeats];

        if (newSelectedSeats.some((selectedSeat) => selectedSeat.seatNumber === seatNumber)) {
            // If the seat is already selected, deselect it
            const index = newSelectedSeats.findIndex((selectedSeat) => selectedSeat.seatNumber === seatNumber);
            newSelectedSeats.splice(index, 1);  // Remove the seat object
        } else {
            // Otherwise, select the seat
            newSelectedSeats.push(seat);  // Add the full seat object
        }

        setSelectedSeats(newSelectedSeats);  // Update selected seats state
    };

    // Trigger onSeatSelect when selection is confirmed
    const handleConfirmSelection = () => {
        onSeatSelect(selectedSeats);  // Pass the selected seats to the parent component
    };

    // useEffect to fetch seats only once when the component mounts
    useEffect(() => {
        if (selectedScreening) {
            loadSeats();  // Only fetch seats when selectedScreening is available
        }
    }, [selectedScreening]);  // Run once when component mounts or when selectedScreening changes

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
                        className={`seat ${selectedSeats.some(selectedSeat => selectedSeat.seatNumber === seat.seatNumber) ? 'selected' : ''}`}
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
