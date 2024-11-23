document.addEventListener('DOMContentLoaded', () => {
    // DOM Elements
    const ordinaryUserButton = document.getElementById('ordinary-user');
    const registeredUserButton = document.getElementById('registered-user');
    const authSection = document.getElementById('auth-section');
    const signupSection = document.getElementById('signup-section');
    const signinSection = document.getElementById('signin-section');
    const signupForm = document.getElementById('signup-form');
    const signinForm = document.getElementById('signin-form');
    const signupSuccess = document.getElementById('signup-success');
    const signinSuccess = document.getElementById('signin-success');
    const theaterSelectionSection = document.getElementById('theater-selection-section');
    const searchBarSection = document.getElementById('search-bar-section');
    const movieSearchInput = document.getElementById('movie-search');
    const moviesSection = document.getElementById('movies-section');
    const seatSelectionSection = document.getElementById('seat-selection-section');
    const paymentSection = document.getElementById('payment-section');
    const receiptSection = document.getElementById('receipt-section');
    const theaterList = document.getElementById('theater-list');
    const movieList = document.getElementById('movie-list');
    const seatMap = document.getElementById('seat-map');
    const totalPriceDisplay = document.getElementById('total-price');
    const finalPriceDisplay = document.getElementById('final-price');
    const receiptDetails = document.getElementById('receipt-details');
    const cancelTicketButton = document.getElementById('cancel-ticket-button');
    const confirmSelectionButton = document.getElementById('confirm-selection-button');
    const paymentForm = document.getElementById('payment-form');

    //Server communication part:
    const runJavaTestButton = document.getElementById('runJavaTestButton');
    const TestInput = document.getElementById('Test-input');
    const TestInputButton = document.getElementById('TestInputButton');

    let selectedSeats = [];
    let currentMovie = null;
    let currentTheater = null;
    let isRegisteredUser = false;
    let purchasedTicket = null; // Stores ticket details after purchase
    const totalSeats = 100;
    let RUSeatsBooked = 0; // Tracks RU seat bookings
    const TICKET_COST = 15; // Cost per seat

    // Debug helper
    const log = (message) => console.log(`[DEBUG]: ${message}`);

    // Ensure that hidden sections behave as expected
    const hideAllSections = () => {
        authSection.classList.add('hidden');
        signupSection.classList.add('hidden');
        signinSection.classList.add('hidden');
        theaterSelectionSection.classList.add('hidden');
        searchBarSection.classList.add('hidden');
        moviesSection.classList.add('hidden');
        seatSelectionSection.classList.add('hidden');
        paymentSection.classList.add('hidden');
        receiptSection.classList.add('hidden');
    };

    // Handle Ordinary User Selection
    ordinaryUserButton.addEventListener('click', () => {
        log('Ordinary User Selected');
        isRegisteredUser = false;
        hideAllSections();
        theaterSelectionSection.classList.remove('hidden');
        loadTheaters();
    });

    // Handle Registered User Selection
    registeredUserButton.addEventListener('click', () => {
        log('Registered User Selected');
        isRegisteredUser = true;
        hideAllSections();
        authSection.classList.remove('hidden');
    });

    // Show Sign Up Form
    document.getElementById('signup-button').addEventListener('click', () => {
        log('Sign Up Button Clicked');
        hideAllSections();
        signupSection.classList.remove('hidden');
    });

    // Show Sign In Form
    document.getElementById('signin-button').addEventListener('click', () => {
        log('Sign In Button Clicked');
        hideAllSections();
        signinSection.classList.remove('hidden');
    });

    // Handle Sign Up
    signupForm.addEventListener('submit', (event) => {
        event.preventDefault();
        log('Sign Up Form Submitted');

        const email = document.getElementById('signup-email').value;
        const password = document.getElementById('signup-password').value;

        if (email && password) {
            signupSuccess.classList.remove('hidden');
            signupForm.reset();

            // Redirect to Sign In after success
            setTimeout(() => {
                hideAllSections();
                signinSection.classList.remove('hidden');
                alert('As an RU, you will be charged a $20 annual membership fee.');
            }, 2000);
        } else {
            alert('Please fill in all required fields.');
        }
    });

    // Handle Sign In
    signinForm.addEventListener('submit', (event) => {
        event.preventDefault();
        log('Sign In Form Submitted');

        const email = document.getElementById('signin-email').value;
        const password = document.getElementById('signin-password').value;

        // Simulated credential check
        if (email && password) {
            signinSuccess.classList.remove('hidden');
            signinForm.reset();

            // Redirect to Theater Selection after success
            setTimeout(() => {
                hideAllSections();
                theaterSelectionSection.classList.remove('hidden');
                loadTheaters();
            }, 2000);
        } else {
            alert('Invalid credentials! Backend integration required.');
        }
    });

    // Theater data
    const theaters = [
        { id: 1, name: "AcmePlex Downtown" },
        { id: 2, name: "AcmePlex Mall" },
        { id: 3, name: "AcmePlex Suburbs" },
    ];

    // Movie data with theater association
    const movies = [
        { id: 1, name: "Inception", theaterId: 1, showtimes: ["12:00 PM", "3:00 PM", "6:00 PM"] },
        { id: 2, name: "The Matrix", theaterId: 2, showtimes: ["1:00 PM", "4:00 PM", "7:00 PM"] },
        { id: 3, name: "Interstellar", theaterId: 3, showtimes: ["2:00 PM", "5:00 PM", "8:00 PM"] },
        { id: 4, name: "Avengers: Endgame", theaterId: 1, showtimes: ["11:00 AM", "2:30 PM", "6:30 PM"] },
        { id: 5, name: "The Dark Knight", theaterId: 2, showtimes: ["12:30 PM", "3:30 PM", "7:30 PM"] },
    ];

    // Load theaters into the theater selection section
    const loadTheaters = () => {
        log('Loading Theaters');
        theaterList.innerHTML = theaters.map(theater => `
            <button onclick="selectTheater(${theater.id})">${theater.name}</button>
        `).join('');
    };

    // Load movies for the selected theater
    const loadMoviesForTheater = (theaterId) => {
        const filteredMovies = movies.filter(movie => movie.theaterId === theaterId);
        movieList.innerHTML = filteredMovies.map(movie => `
            <div class="movie-card" data-movie-name="${movie.name.toLowerCase()}">
                <h3>${movie.name}</h3>
                <p>Showtimes: ${movie.showtimes.join(", ")}</p>
                <button onclick="selectMovie(${movie.id})">Select</button>
            </div>
        `).join('');
        searchBarSection.classList.remove('hidden'); // Show search bar
    };

    // Movie Search Functionality
    movieSearchInput.addEventListener('input', () => {
        const query = movieSearchInput.value.toLowerCase();
        document.querySelectorAll('.movie-card').forEach(card => {
            const movieName = card.getAttribute('data-movie-name');
            card.style.display = movieName.includes(query) ? 'block' : 'none';
        });
    });

    // Theater selection handler
    window.selectTheater = (id) => {
        log(`Theater Selected: ${id}`);
        currentTheater = theaters.find(theater => theater.id === id);
        hideAllSections();
        moviesSection.classList.remove('hidden');
        loadMoviesForTheater(id);
    };

    // Movie selection handler
    window.selectMovie = (id) => {
        log(`Movie Selected: ${id}`);
        currentMovie = movies.find(movie => movie.id === id);
        hideAllSections();
        seatSelectionSection.classList.remove('hidden');
        generateSeats();
    };

    // Seat selection and payment
    const generateSeats = () => {
        log('Generating Seats');
        seatMap.innerHTML = '';
        selectedSeats = [];
        for (let i = 0; i < 100; i++) {
            const seat = document.createElement('div');
            seat.classList.add('seat');
            seat.addEventListener('click', () => {
                if (!seat.classList.contains('taken')) {
                    seat.classList.toggle('selected');
                    if (seat.classList.contains('selected')) {
                        selectedSeats.push(i + 1);
                    } else {
                        selectedSeats = selectedSeats.filter(seatNum => seatNum !== i + 1);
                    }
                    updateTotalPrice();
                }
            });
            seatMap.appendChild(seat);
        }
    };

    const updateTotalPrice = () => {
        const totalCost = selectedSeats.length * TICKET_COST;
        totalPriceDisplay.textContent = `Total Price: $${totalCost}`;
    };

    confirmSelectionButton.addEventListener('click', () => {
        if (selectedSeats.length === 0) {
            alert('Please select at least one seat.');
            return;
        }

        if (isRegisteredUser && RUSeatsBooked + selectedSeats.length > totalSeats * 0.1) {
            alert('RU seat limit exceeded. Only 10% of the seats can be booked before public announcements.');
            return;
        }

        if (isRegisteredUser) RUSeatsBooked += selectedSeats.length;

        hideAllSections();
        paymentSection.classList.remove('hidden');
        finalPriceDisplay.textContent = `Final Price: $${selectedSeats.length * TICKET_COST}`;
    });

    // Payment and Ticket Receipt
    paymentForm.addEventListener('submit', (event) => {
        event.preventDefault();
        log('Payment Submitted');

        purchasedTicket = {
            movie: currentMovie.name,
            theater: currentTheater.name,
            seats: selectedSeats,
            totalCost: selectedSeats.length * TICKET_COST,
            purchaseTime: new Date(),
        };

        receiptDetails.innerHTML = `
            <p><strong>Movie:</strong> ${purchasedTicket.movie}</p>
            <p><strong>Theater:</strong> ${purchasedTicket.theater}</p>
            <p><strong>Seats:</strong> ${purchasedTicket.seats.join(', ')}</p>
            <p><strong>Total Cost:</strong> $${purchasedTicket.totalCost}</p>
            <p><strong>Purchase Time:</strong> ${purchasedTicket.purchaseTime}</p>
        `;

        hideAllSections();
        receiptSection.classList.remove('hidden');
    });

    // Cancellation Logic
    cancelTicketButton.addEventListener('click', () => {
        if (!purchasedTicket) return alert('No ticket to cancel!');

        const currentTime = new Date();
        const timeDiff = currentTime - new Date(purchasedTicket.purchaseTime);
        const hoursDiff = timeDiff / (1000 * 60 * 60); // Convert ms to hours

        if (hoursDiff > 72) {
            alert('Cancellation not allowed beyond 72 hours of purchase.');
            return;
        }

        let refundAmount = purchasedTicket.totalCost;
        if (!isRegisteredUser) {
            refundAmount -= (refundAmount * 0.15); // 15% admin fee for ordinary users
        }

        alert(`Ticket canceled. Refund: $${refundAmount.toFixed(2)}`);
        purchasedTicket = null; // Reset ticket details
        receiptSection.classList.add('hidden');
        theaterSelectionSection.classList.remove('hidden');
    });

    //Server communication test part:
    runJavaTestButton.addEventListener('click', () => {
    // Send a request to the server.js
        fetch('http://localhost:3000/TestProgram')
            .then(response => {
                // Processing Response
                if (!response.ok) {
                    throw new Error('Network Response Failure');
                }
                return response.text();  // can use text(), json(), blob(), etc.
            })
            .then(data => {
                document.getElementById('output').textContent = data;
                console.log(data);  // Processing of acquired web content
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('output').textContent = 'Error occurred while running Java program.';
            });
    });

    TestInputButton.addEventListener('click', () => {
        requestInput = TestInput.value
        // Send a request to the server.js
        fetch('http://localhost:3000/TestProgram',{
            method: 'POST',
            headers: {
            'Content-Type': 'application/json'  // Tell the server that the request is JSON
            },
            body: JSON.stringify({ requestInput})
            // body: "Debug1."
        })
            .then(response => {
                // Processing Response
                if (!response.ok) {
                    throw new Error('Network Response Failure');
                }
                return response.text();  // can use text(), json(), blob(), etc.
            })
            .then(data => {
                document.getElementById('output').textContent = data;
                console.log(data);  // Processing of acquired web content
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('output').textContent = 'Error occurred while running Java program.';
            });
    });
});
