import React, { useState } from 'react';
import axios from 'axios';

function GuestCheckin({ onGuestCheckin }) {
    // Define state variables for form inputs
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    // Handle form field changes
    const handleChange = (e) => {
        const { id, value } = e.target;
        if (id === 'guest-name') setName(value);
        if (id === 'guest-email') setEmail(value);
    };

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();

        // Create the guest data object
        const guestData = {
            name,
            email,
        };

        // Reset any previous error or success messages
        setErrorMessage('');
        setSuccessMessage('');

        axios.post('http://localhost:8083/guest-checkin', guestData)
            .then((response) => {
                // Guest checked in successfully
                setSuccessMessage('Check-in successful! Welcome!');
                //Returns an ordinaryUser object
                onGuestCheckin(response.data);
            })
            .catch((error) => {
                // Error during check-in
                setErrorMessage('Error during check-in. Please try again.');
            });
    };

    return (
        <section id="guest-checkin-section">
            <h2>Guest Check-in</h2>
            <form id="guest-checkin-form" onSubmit={handleSubmit}>
                <div className='guest-checkin-item'>
                    <label htmlFor="guest-name">Name:</label>
                    <input
                        type="text"
                        id="guest-name"
                        value={name}
                        onChange={handleChange}
                        placeholder="Enter your name"
                        required
                    />
                </div>
                <div className='guest-checkin-item'>
                    <label htmlFor="guest-email">Email:</label>
                    <input
                        type="email"
                        id="guest-email"
                        value={email}
                        onChange={handleChange}
                        placeholder="Enter your email"
                        required
                    />
                </div>
                <button type="submit">Check In</button>
            </form>

            {successMessage && <p id="guest-checkin-success" className="success">{successMessage}</p>}
            {errorMessage && <p id="guest-checkin-error" className="error">{errorMessage}</p>}
        </section>
    );
}

export default GuestCheckin;
