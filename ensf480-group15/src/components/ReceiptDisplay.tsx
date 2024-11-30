import React from 'react';

function ReceiptDisplay({ ticket }) {
    return (
        <section id="receipt-section">
            <h2>Ticket Receipt</h2>
            <div id="receipt-details">
                <h3>Movie: {ticket.screening.movie.title}</h3>
                <p>Theater: {ticket.screening.theatre.name}</p>
                <p>Seats: {ticket.receipt.seatList.map(seat => seat.seatNumber).join(', ')}</p>
                <p>Total Cost: ${ticket.receipt.totalPrice}</p>
                <p>Showtime: {ticket.screening.screenDate}</p>
            </div>
        </section>
    );
}

export default ReceiptDisplay;
