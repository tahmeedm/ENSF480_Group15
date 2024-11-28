import React, { useState } from 'react';
import axios from 'axios';

function UserSelection({ onUserSelect }) {
    // State for managing the output text
    const [output, setOutput] = useState('');
    // State for managing the user input
    const [userInput, setUserInput] = useState('');

    // Handle Registered User Selection
    const handleRegisteredUserSelection = () => {
        console.log('Registered User Selected');
        onUserSelect(true); // Pass true to indicate Registered User
    };

    // Handle Ordinary User Selection
    const handleOrdinaryUserSelection = () => {
        console.log('Ordinary User Selected');
        onUserSelect(false); // Pass false to indicate Ordinary User
    };

    const handleTestButton = () => {
        // Check if input value is not empty
        if (userInput.trim() !== '') {
            // Split the input string by spaces and filter out empty entries
            const args = userInput.split(' ').filter(arg => arg.trim() !== '');

            // If the input contains valid args, send the request
            if (args.length > 0) {
                // Join the array into a single string with space separation
                const argsString = args.join(' ');  // This will create a single string like "adsfse"

                // Make the request with the args string
                axios.get('http://localhost:8083/test', {
                    params: {
                        args: argsString  // Send the string version of args
                    }
                })
                    .then(response => {
                        // Set the output state with the response data
                        setOutput(response.data);
                        console.log(response.data);  // Log the response data for debugging
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        if (error.response) {
                            // If the server responded with an error status code
                            console.error('Response data:', error.response.data);
                            setOutput(error.response.data);  // Display detailed error from the server
                        } else if (error.request) {
                            // If no response was received
                            console.error('Request data:', error.request);
                        } else {
                            // For other errors related to setting up the request
                            console.error('Error message:', error.message);
                        }
                    });
            } else {
                // If there are no valid args after splitting, ask for input
                setOutput('Please enter valid input.');
            }
        } else {
            // If the input is empty, prompt the user to enter some input
            setOutput('Please enter some input.');
        }
    };



    return (
        <section id="user-selection">
            <h2>Select User Type</h2>
            <button id="ordinary-user" onClick={handleOrdinaryUserSelection}>Ordinary User</button>
            <button id="registered-user" onClick={handleRegisteredUserSelection}>Registered User (RU)</button>

            {/* For server testing */}
            <h1>Click the button to test running Java program (print value of TestProgram in backend)</h1>
            <button id="runJavaTestButton">Run Java(Test)</button>
            {/* Input field to get user input */}
            <input
                type="text"
                id="Test-input"
                placeholder="Enter your input for testing server post method"
                value={userInput}
                onChange={(e) => setUserInput(e.target.value)}  // Update the user input state on change
            />
            <button id="TestInputButton" onClick={handleTestButton}>Submit</button>

            {/* Render output */}
            <pre>{output}</pre>
        </section>
    );
}

export default UserSelection;
