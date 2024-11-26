import React from 'react';

function UserSelection({ onUserSelect }) {

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

    return (
        <section id="user-selection">
            <h2>Select User Type</h2>
            <button id="ordinary-user" onClick={handleOrdinaryUserSelection}>Ordinary User</button>
            <button id="registered-user" onClick={handleRegisteredUserSelection}>Registered User (RU)</button>

            {/* <!-- For server testing --> */}
            <h1>Click the button to test running Java program (print value of TestProgram in backend)</h1>
            <button id="runJavaTestButton">Run Java(Test)</button>
            <input type="text" id="Test-input" placeholder="Enter your input for testing server post method" />
            <button id="TestInputButton">Submit</button>
            <pre id="output"></pre>
        </section>
    );
}

export default UserSelection;
