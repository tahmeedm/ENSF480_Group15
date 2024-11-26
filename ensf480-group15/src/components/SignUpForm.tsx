import React, { useState } from 'react';

function SignUpForm() {
    // Define state variables for form inputs
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [address, setAddress] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    // Handle form field changes
    const handleChange = (e) => {
        const { id, value } = e.target;
        if (id === 'signup-name') setName(value);
        if (id === 'signup-email') setEmail(value);
        if (id === 'signup-password') setPassword(value);
        if (id === 'signup-address') setAddress(value);
    };

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();

        // Create the user object
        const userData = {
            name,
            email,
            password,
            address,
        };

        // Reset any previous error or success messages
        setErrorMessage('');
        setSuccessMessage('');

        try {
            // Send data to the API using fetch
            const response = await fetch('https://example.com/api/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            });

            // Check if the response is successful
            if (response.ok) {
                setSuccessMessage('Sign Up Successful! You can now sign in.');
            } else {
                // Handle errors (e.g., invalid input, server error)
                const errorData = await response.json();
                setErrorMessage(errorData.message || 'Something went wrong. Please try again.');
            }
        } catch (error) {
            // Catch network or other errors
            setErrorMessage('Failed to connect to the server. Please try again.');
        }
    };

    return (
        <section id="signup-section">
            <h2>Sign Up</h2>
            <form id="signup-form" onSubmit={handleSubmit}>
                <label htmlFor="signup-name">Name:</label>
                <input
                    type="text"
                    id="signup-name"
                    value={name}
                    onChange={handleChange}
                    placeholder="Enter your name"
                    required
                />

                <label htmlFor="signup-email">Email:</label>
                <input
                    type="email"
                    id="signup-email"
                    value={email}
                    onChange={handleChange}
                    placeholder="Enter your email"
                    required
                />

                <label htmlFor="signup-password">Password:</label>
                <input
                    type="password"
                    id="signup-password"
                    value={password}
                    onChange={handleChange}
                    placeholder="Enter your password"
                    required
                />

                <label htmlFor="signup-address">Address:</label>
                <input
                    type="text"
                    id="signup-address"
                    value={address}
                    onChange={handleChange}
                    placeholder="Enter your address"
                    required
                />

                <button type="submit">Sign Up</button>
            </form>

            {successMessage && <p id="signup-success" className="success">{successMessage}</p>}
            {errorMessage && <p id="signup-error" className="error">{errorMessage}</p>}
        </section>
    );
}

export default SignUpForm;
