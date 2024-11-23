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
    const moviesSection = document.getElementById('movies-section');
    const seatSelectionSection = document.getElementById('seat-selection-section');
    const paymentSection = document.getElementById('payment-section');
    const theaterList = document.getElementById('theater-list');
    const movieList = document.getElementById('movie-list');
    const seatMap = document.getElementById('seat-map');
    const confirmSelectionButton = document.getElementById('confirm-selection-button');
    const paymentForm = document.getElementById('payment-form');
    const totalPriceDisplay = document.getElementById('total-price');
    const finalPriceDisplay = document.getElementById('final-price');

    let selectedSeats = [];
    let currentMovie = null;
    let currentTheater = null;
    let isRegisteredUser = false;
    const TICKET_COST = 15; // Cost per seat

    // Debug helper
    const log = (message) => console.log(`[DEBUG]: ${message}`);

    // Ensure that hidden sections behave as expected
    const hideAllSections = () => {
        authSection.classList.add('hidden');
        signupSection.classList.add('hidden');
        signinSection.classList.add('hidden');
        theaterSelectionSection.classList.add('hidden');
        moviesSection.classList.add('hidden');
        seatSelectionSection.classList.add('hidden');
        paymentSection.classList.add('hidden');
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
            <div class="movie-card">
                <h3>${movie.name}</h3>
                <p>Showtimes: ${movie.showtimes.join(", ")}</p>
                <button onclick="selectMovie(${movie.id})">Select</button>
            </div>
        `).join('');
    };

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
        hideAllSections();
        paymentSection.classList.remove('hidden');
        finalPriceDisplay.textContent = `Final Price: $${selectedSeats.length * TICKET_COST}`;
    });

    paymentForm.addEventListener('submit', (event) => {
        event.preventDefault();
        log('Payment Submitted');
        alert('Payment successful!');
        hideAllSections();
        theaterSelectionSection.classList.remove('hidden');
    });
});
