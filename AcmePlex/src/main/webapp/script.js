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

    // Fetch theaters from the backend
    const loadTheaters = async () => {
        log('Loading Theaters');
        try {
            const response = await fetch('/api/theaters'); // Replace with actual API endpoint
            const theaters = await response.json();
            theaterList.innerHTML = theaters.map(theater => 
                `<button onclick="selectTheater(${theater.id})">${theater.name}</button>`
            ).join('');
        } catch (error) {
            log('Error fetching theaters:', error);
        }
    };

    // Fetch movies for a selected theater from the backend
    const loadMoviesForTheater = async (theaterId) => {
        log(`Loading Movies for Theater ID: ${theaterId}`);
        try {
            const response = await fetch(`/api/movies?theaterId=${theaterId}`); // Replace with actual API endpoint
            const movies = await response.json();
            movieList.innerHTML = movies.map(movie => 
                `<div class="movie-card" data-movie-name="${movie.name.toLowerCase()}">
                    <h3>${movie.name}</h3>
                    <p>Showtimes: ${movie.showtimes.join(", ")}</p>
                    <button onclick="selectMovie(${movie.id})">Select</button>
                </div>`
            ).join('');
            searchBarSection.classList.remove('hidden'); // Show search bar
        } catch (error) {
            log('Error fetching movies:', error);
        }
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
        currentTheater = { id }; // Save selected theater ID
        hideAllSections();
        moviesSection.classList.remove('hidden');
        loadMoviesForTheater(id);
    };

    // Movie selection handler
    window.selectMovie = (id) => {
        log(`Movie Selected: ${id}`);
        currentMovie = { id }; // Save selected movie ID
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

    // Update total price display
    const updateTotalPrice = () => {
        const totalPrice = selectedSeats.length * TICKET_COST;
        totalPriceDisplay.textContent = `$${totalPrice}`;
    };

    // Handle ticket confirmation
    confirmSelectionButton.addEventListener('click', () => {
        if (selectedSeats.length === 0) {
            alert('Please select seats.');
        } else {
            purchasedTicket = {
                movie: currentMovie.name,
                theater: currentTheater.name,
                seats: selectedSeats,
                totalPrice: selectedSeats.length * TICKET_COST
            };
            finalPriceDisplay.textContent = `$${purchasedTicket.totalPrice}`;
            hideAllSections();
            paymentSection.classList.remove('hidden');
        }
    });

    // Handle payment form submission
    paymentForm.addEventListener('submit', (event) => {
        event.preventDefault();
        log('Payment Form Submitted');
        hideAllSections();
        receiptSection.classList.remove('hidden');
        receiptDetails.innerHTML = `
            <h3>Receipt</h3>
            <p><strong>Movie:</strong> ${purchasedTicket.movie}</p>
            <p><strong>Theater:</strong> ${purchasedTicket.theater}</p>
            <p><strong>Seats:</strong> ${purchasedTicket.seats.join(", ")}</p>
            <p><strong>Total Price:</strong> $${purchasedTicket.totalPrice}</p>
        `;
    });

    // Handle ticket cancellation
    cancelTicketButton.addEventListener('click', () => {
        purchasedTicket = null;
        hideAllSections();
        theaterSelectionSection.classList.remove('hidden');
    });
});
