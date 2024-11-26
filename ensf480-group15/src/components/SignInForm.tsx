import React, { useState } from 'react';

function SignInForm({ onUserSelect, setPaymentInfo }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [signInSuccess, setSignInSuccess] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    // Handle form submission
    const handleSubmit = (event) => {
        event.preventDefault();

        // Basic validation (can be extended with backend validation)
        if (!email || !password) {
            setErrorMessage("Please enter both email and password.");
            return;
        }

        // Simulating a sign-in process (replace with real API call)
        if (email === 'user@example.com' && password === 'password123') {
            setSignInSuccess(true);
            setErrorMessage('');
            onUserSelect(true);
            setPaymentInfo({ cardNumber: '1234 5678 9012 3456', expiryDate: '12/24', cvv: '123' });
        } else {
            setErrorMessage("Invalid email or password. Please try again.");
            setSignInSuccess(false);
        }
    };


    return (
        <section id="signin-section">
            <h2>Sign In</h2>
            <form id="signin-form" onSubmit={handleSubmit}>
                <label htmlFor="signin-email">Email:</label>
                <input
                    type="email"
                    id="signin-email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Enter your email"
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
