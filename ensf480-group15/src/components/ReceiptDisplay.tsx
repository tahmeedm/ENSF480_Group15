import React from 'react';

function ReceiptDisplay({ ticket }) {
    return (
        <section id="receipt-section">
            <h2>Ticket Receipt</h2>
            <div id="receipt-details">
                <h3>Movie: {ticket.movieName}</h3>
                <p>Theater: {ticket.theaterName}</p>
                <p>Seats: {ticket.seats.join(', ')}</p>
                <p>Total Cost: ${ticket.totalPrice}</p>
                <p>Showtime: {ticket.showtime}</p>
            </div>
        </section>
    );
}

export default ReceiptDisplay;
