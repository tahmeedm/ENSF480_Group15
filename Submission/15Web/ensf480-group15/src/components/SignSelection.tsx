import React from 'react'


function SignSelection({ onUserSelect }) {

    const handleSignUpClick = () => {
        console.log('Sign Up button clicked');
        onUserSelect(true); // Pass true to indicate Sign Up
    };

    const handleSignInClick = () => {
        console.log('Sign In button clicked');
        onUserSelect(false); // Pass false to indicate Sign In
    };

    return (
        <section id="auth-section">
            <h2>Sign Up / Sign In</h2>
            <p>Do you have an account?</p>
            <button id="signup-button" onClick={handleSignUpClick} >Sign Up</button>
            <button id="signin-button" onClick={handleSignInClick} >Sign In</button>
        </section>
    )
}

export default SignSelection