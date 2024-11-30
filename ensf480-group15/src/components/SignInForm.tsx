import React, { useState } from 'react';
import axios from 'axios';

function SignInForm({ onUserSelect }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [signInSuccess, setSignInSuccess] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    // Handle form submission
    const handleSubmit = (event) => {
        event.preventDefault();

        // Basic validation (can be extended with backend validation)
        if (!username || !password) {
            setErrorMessage("Please enter both username and password.");
            return;
        }

        //Real API Call
        axios.post('http://localhost:8083/signin', { username, password })
            .then(response => {
                // Assuming response.data contains the user object
                setSignInSuccess(true);
                setErrorMessage('');
                onUserSelect(true, response.data);
            })
            .catch(error => {
                setErrorMessage("Invalid username or password. Please try again.");
                setSignInSuccess(false);
            });

        // Simulating a sign-in process (replace with real API call)
        // if (username === 'user@example.com' && password === 'password123') {
        //     setSignInSuccess(true);
        //     setErrorMessage('');
        //     onUserSelect(true);
        //     setPaymentInfo({ cardNumber: '1234 5678 9012 3456', expiryDate: '12/24', cvv: '123' });
        // } else {
        //     setErrorMessage("Invalid username or password. Please try again.");
        //     setSignInSuccess(false);
        // }
    };


    return (
        <section id="signin-section">
            <h2>Sign In</h2>
            <p>For returning users of AcmePlex</p>
            <form id="signin-form" onSubmit={handleSubmit}>
                <label htmlFor="signin-username">Username:</label>
                <input
                    type="username"
                    id="signin-username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder="Enter your username"
                    required
                />
                <label htmlFor="signin-password">Password:</label>
                <input
                    type="password"
                    id="signin-password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Enter your password"
                    required
                />
                <button type="submit">Sign In</button>
            </form>

            {signInSuccess && (
                <p id="signin-success">Sign In Successful! Welcome back!</p>
            )}

            {errorMessage && (
                <p id="signin-error" style={{ color: 'red' }}>
                    {errorMessage}
                </p>
            )}
        </section>
    );
}

export default SignInForm;
