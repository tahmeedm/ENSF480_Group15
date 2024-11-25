!import React from 'react'
import { useState } from 'react';

function SignInForm() {


    return (
        <section id="signin-section" class="hidden">
            <h2>Sign In</h2>
            <form id="signin-form">
                <label for="signin-email">Email:</label>
                <input type="email" id="signin-email" placeholder="Enter your email" required />
                <label for="signin-password">Password:</label>
                <input type="password" id="signin-password" placeholder="Enter your password" required />
                <button type="submit">Sign In</button>
            </form>
            <p id="signin-success" class="hidden">Sign In Successful! Welcome back!</p>
        </section>
    )
}

export default SignInForm