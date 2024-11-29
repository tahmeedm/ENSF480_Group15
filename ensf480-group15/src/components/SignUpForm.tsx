import React, { useState } from 'react';
import axios from 'axios';

function SignUpForm({ onUserSelect }) {
    // Define state variables for form inputs
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [address, setAddress] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    // Handle form field changes
    const handleChange = (e) => {
        const { id, value } = e.target;
        if (id === 'signup-name') setName(value);
        if (id === 'signup-email') setEmail(value);
        if (id === 'signup-username') setUsername(value);
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
            username,
            password,
            address,
        };

        // Reset any previous error or success messages
        setErrorMessage('');
        setSuccessMessage('');

        axios.post('http://localhost:8083/signup', userData)
            .then((response) => {
                // User created successfully
                setSuccessMessage('User created successfully! Logging you in!');
                onUserSelect(true, response.data);
            })
            .catch((error) => {
                // Error creating user
                setErrorMessage('Error creating user. Please try again.');
            });
    };

    return (
        <section id="signup-section">
            <h2>Sign Up</h2>
            <form id="signup-form" onSubmit={handleSubmit}>
                <div className='signup-item'>
                    <label htmlFor="signup-name">Name:</label>
                    <input
                        type="text"
                        id="signup-name"
                        value={name}
                        onChange={handleChange}
                        placeholder="Enter your name"
                        required
                    />
                </div>
                <div className='signup-item'>
                    <label htmlFor="signup-email">Email:</label>
                    <input
                        type="email"
                        id="signup-email"
                        value={email}
                        onChange={handleChange}
                        placeholder="Enter your email"
                        required
                    />
                </div>
                <div className='signup-item'>
                    <label htmlFor="signup-username">Username:</label>
                    <input
                        type="text"
                        id="signup-username"
                        value={username}
                        onChange={handleChange}
                        placeholder="Enter your username"
                        required
                    />
                </div>
                <div className='signup-item'>
                    <label htmlFor="signup-password">Password:</label>
                    <input
                        type="password"
                        id="signup-password"
                        value={password}
                        onChange={handleChange}
                        placeholder="Enter your password"
                        required
                    />
                </div>
                <div className='signup-item'>
                    <label htmlFor="signup-address">Address:</label>
                    <input
                        type="text"
                        id="signup-address"
                        value={address}
                        onChange={handleChange}
                        placeholder="Enter your address"
                        required
                    />
                </div>
                <button type="submit">Sign Up</button>
            </form>

            {successMessage && <p id="signup-success" className="success">{successMessage}</p>}
            {errorMessage && <p id="signup-error" className="error">{errorMessage}</p>}
        </section >
    );
}

export default SignUpForm;
