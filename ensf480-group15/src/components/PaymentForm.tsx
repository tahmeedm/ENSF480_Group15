import React, { useEffect, useState } from 'react';

function PaymentForm({
    onPurchase,
    setPaymentInfo,
    paymentInfo,
    selectedSeats,
    seatCost,
    currentMovie,
    currentTheater
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

    // Calculate total price based on selected seats and seat cost
    const totalPrice = selectedSeats.length * seatCost;

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        // Send payment info to parent
        setPaymentInfo(formData);

        const purchaseDetails = {
            movieName: currentMovie.name,
            theaterName: currentTheater.name,
            showtime: currentMovie.showtimes[0], // Assuming we use the first showtime, modify as needed
            seats: selectedSeats,
            totalPrice: totalPrice,
            paymentInfo: formData,
            seatCost: seatCost,
        };

        onPurchase(purchaseDetails);
        console.log('Purchase Details:', purchaseDetails);
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
            <p id="final-price">Total Price: ${totalPrice}</p>

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