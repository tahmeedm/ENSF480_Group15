import axios from 'axios';
import React, { useEffect, useState } from 'react';

function PaymentForm({
    onPurchase,
    proceedToReceipt,
    setPaymentInfo,
    paymentInfo,
    selectedSeats,
    price,
    selectedScreening,
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

        // Extract necessary details
        const transactionDate = new Date().toISOString();
        const totalPrice = price;
        const selectedSeatIds = selectedSeats.map((seat) => seat.id); // Use seat.id instead of seatNumber

        console.log("debug");
        console.log("selectedScreening:", selectedScreening.id);

        fetch('http://localhost:8083/ticket-bookings', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                screeningId: selectedScreening.id,
                paymentInfo: {
                    cardNumber: formData.cardNumber,
                    expiryDate: formData.expiryDate,
                    cvv: formData.cvv
                },
                transactionDate: transactionDate,
                totalPrice: totalPrice,
                seats: selectedSeatIds
            })
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                }
            })
            .then(data => console.log("Response data:", data));

        // axios.post('http://localhost:8083/ticket-bookings')

        // Send data to API directly as parameters
        // axios.post('http://localhost:8083/ticket-bookings', {
        //     screeningId: selectedScreening.id,
        //     paymentInfo: {
        //         cardNumber: formData.cardNumber,
        //         expiryDate: formData.expiryDate,
        //         cvv: formData.cvv
        //     },
        //     transactionDate: transactionDate,
        //     totalPrice: totalPrice,
        //     seats: selectedSeatIds
        // })
        //     .then(response => {
        //         console.log('Purchase successful:', response.data);
        //         onPurchase(response.data); // Pass the successful purchase data
        //         proceedToReceipt(true);
        //     })
        //     .catch(error => {
        //         console.error('Error making purchase:', error);
        //         // alert('Error making purchase. Please try again.');
        //     });
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
