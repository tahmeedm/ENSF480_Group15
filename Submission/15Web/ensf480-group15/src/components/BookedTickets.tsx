import React, { useEffect, useState } from 'react';
import axios from 'axios';

function BookedTickets({ userId }) {
    const [bookings, setBookings] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const response = await axios.get(`http://localhost:8083/bookings/${userId}`);

                if (!response.data || !Array.isArray(response.data)) {
                    throw new Error('Unexpected data format received.');
                }

                setBookings(response.data);
            } catch (error) {
                setError('Error fetching bookings.');
                setBookings([]);
            } finally {
                setLoading(false);
            }
        };

        fetchBookings();
    }, [userId]);

    const handleCancelBooking = (bookingId) => {
        setBookings((prevBookings) => prevBookings.filter((b) => b.id !== bookingId));

        axios.delete(`http://localhost:8083/bookings/${bookingId}`)
            .then(() => {
                console.log('Booking canceled.');
            })
            .catch((error) => {
                console.error('Error canceling booking:', error);
                setBookings((prevBookings) => [...prevBookings, { id: bookingId }]);
            });
    };

    if (loading) return <p>Loading bookings...</p>;
    if (error) return <p>{error}</p>;

    // If there are no bookings or bookings is an empty array, show 'No bookings found.'
    if (!bookings.length) {
        return <p>No bookings found.</p>;
    }

    return (
        <section id="menu-selection-section">
            <h2>Booked Tickets</h2>
            {/* Render each booking if there are valid bookings */}
            {bookings.map((booking) => (
                <div key={booking.id} className="booking-item">
                    <h3>{booking.screening.movie.title}</h3>
                    <p>{booking.screening.screenDate}</p>
                    <button onClick={() => handleCancelBooking(booking.id)}>Cancel</button>
                </div>
            ))}
        </section>
    );
}

export default BookedTickets;

