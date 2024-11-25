import './styles.css';
// import SignInForm from './components/SignInForm';


function App() {



  return (
    <body>
      <header>
        <h1>AcmePlex Theater Ticket Reservation</h1>
      </header>
      <main>
        {/* <!-- User Type Selection --> */}
        <section id="user-selection">
          <h2>Select User Type</h2>
          <button id="ordinary-user">Ordinary User</button>
          <button id="registered-user">Registered User (RU)</button>

          {/* <!-- For server testing--> */}
          <h1>Click the button Test runing Java program(print value of TestProgram in backend)</h1>
          <button id="runJavaTestButton">Run Java(Test)</button>
          <input type="text" id="Test-input" placeholder="Enter your input for testing server post method" />
          <button id="TestInputButton">submit</button>
          <pre id="output"></pre>
        </section>

        {/* <!-- Sign Up / Sign In Section --> */}
        <section id="auth-section" class="hidden">
          <h2>Sign Up / Sign In</h2>
          <button id="signup-button">Sign Up</button>
          <button id="signin-button">Sign In</button>
        </section>

        {/* <!-- Sign Up Form --> */}
        <section id="signup-section" class="hidden">
          <h2>Sign Up</h2>
          <form id="signup-form">
            <label for="signup-name">Name:</label>
            <input type="text" id="signup-name" placeholder="Enter your name" required />
            <label for="signup-email">Email:</label>
            <input type="email" id="signup-email" placeholder="Enter your email" required />
            <label for="signup-password">Password:</label>
            <input type="password" id="signup-password" placeholder="Enter your password" required />
            <label for="signup-address">Address:</label>
            <input type="text" id="signup-address" placeholder="Enter your address" required />
            <label for="signup-card">Card Number:</label>
            <input type="text" id="signup-card" placeholder="Enter your card number" required />
            <button type="submit">Sign Up</button>
          </form>
          <p id="signup-success" class="hidden">Sign Up Successful! You can now sign in.</p>
        </section>

        {/* <!-- Sign In Form --> */}
        {/* <SignInForm /> */}

        {/* <!-- Theater Selection Section --> */}
        <section id="theater-selection-section" class="hidden">
          <h2>Select a Theater</h2>
          <div id="theater-list"></div>
        </section>

        {/* <!-- Search Bar Section --> */}
        <section id="search-bar-section" class="hidden">
          <h2>Search for Movies</h2>
          <input type="text" id="movie-search" placeholder="Search for a movie..." />
        </section>

        {/* <!-- Movies Section --> */}
        <section id="movies-section" class="hidden">
          <h2>Available Movies</h2>
          <div id="movie-list"></div>
        </section>

        {/* <!-- Seat Selection Section --> */}
        <section id="seat-selection-section" class="hidden">
          <h2>Select Your Seats</h2>
          <div id="seat-map"></div>
          <p id="total-price">Total Price: $0</p>
          <button id="confirm-selection-button">Confirm Selection</button>
        </section>

        {/* <!-- Payment Section --> */}
        <section id="payment-section" class="hidden">
          <h2>Payment</h2>
          <p id="final-price"></p>
          <form id="payment-form">
            <label for="card-number">Card Number:</label>
            <input type="text" id="card-number" required />
            <label for="expiry-date">Expiry Date:</label>
            <input type="text" id="expiry-date" required />
            <label for="cvv">CVV:</label>
            <input type="text" id="cvv" required />
            <button type="submit">Pay</button>
          </form>
        </section>

        {/* <!-- Receipt Section --> */}
        <section id="receipt-section" class="hidden">
          <h2>Ticket Receipt</h2>
          <div id="receipt-details"></div>
          <button id="cancel-ticket-button">Cancel Ticket</button>
        </section>
      </main>
    </body>
  );
}

export default App;
