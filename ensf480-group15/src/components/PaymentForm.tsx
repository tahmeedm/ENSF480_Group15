import axios from 'axios';
import React, { useEffect, useState } from 'react';

function PaymentForm({
    onPurchase,
    setPaymentInfo,
    paymentInfo,
    selectedSeats,
    currentMovie,
    currentTheater,
    seatingArrangement,
    price,
    screenDate,
}) {
    const [formData, setFormData] = useState({
        cardNumber: paymentInfo?.cardNumber || '',
        expiryDate: paymentInfo?.expiryDate || '',
        cvv: paymentInfo?.cvv || ''
    });

    // Handle form field changes
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        // Send payment info to parent
        setPaymentInfo(formData);

        // const purchaseDetails = {
        //     movieName: currentMovie.name,
        //     theaterName: currentTheater.name,
        //     showtime: currentMovie.showtimes[0], // Assuming we use the first showtime, modify as needed
        //     seats: selectedSeats,
        //     totalPrice: totalPrice,
        //     paymentInfo: formData,
        //     seatCost: seatCost,
        // };

        const TicketBooking = {
            screening: {
                theatre: currentTheater,
                movie: currentMovie,
                screenDate: screenDate,
                openDate: currentMovie.releaseDate,
                seatList: seatingArrangement,
            },
            receipt: {
                paymentInfo: formData,
                transactionDate: new Date().toISOString(),
                totalPrice: price
            },
            seats: selectedSeats,
        };

        // Send Form Data to API
        axios.post('http://localhost:8083/ticket-bookings', TicketBooking)
            .then(response => {
                console.log('Purchase successful:', response.data);
                onPurchase(TicketBooking);
            })
            .catch(error => {
                console.error('Error making purchase:', error);
                alert('Error making purchase. Please try again.');
            });

        console.log('Purchase Details:', TicketBooking);
    };

    // Autofill form when paymentInfo changes
    useEffect(() => {
        setFormData({
            cardNumber: paymentInfo?.cardNumber || '',
            expiryDate: paymentInfo?.expiryDate || '',
            cvv: paymentInfo?.cvv || ''
        });
        console.log(paymentInfo); // For debugging
    }, [paymentInfo]);

    return (
        <section id="payment-section">
            <h2>Payment</h2>

            {/* Display total price dynamically */}
            <p id="final-price">Total Price: ${price}</p>

            <form id="payment-form" onSubmit={handleSubmit}>
                <label htmlFor="cardNumber">Card Number:</label>
                <input
                    type="text"
                    id="cardNumber"
                    name="cardNumber"
                    value={formData.cardNumber}
                    onChange={handleInputChange}
                    required
                />
                <label htmlFor="expiryDate">Expiry Date:</label>
                <input
                    type="text"
                    id="expiryDate"
                    name="expiryDate"
                    value={formData.expiryDate}
                    onChange={handleInputChange}
                    required
                />
                <label htmlFor="cvv">CVV:</label>
                <input
                    type="text"
                    id="cvv"
                    name="cvv"
                    value={formData.cvv}
                    onChange={handleInputChange}
                    required
                />
                <button type="submit">Pay</button>
            </form>
        </section>
    );
}

export default PaymentForm;
