document.addEventListener('DOMContentLoaded', () => {
    const movies = [
        { id: 1, name: "Inception", showtimes: ["12:00 PM", "3:00 PM", "6:00 PM"] },
        { id: 2, name: "The Matrix", showtimes: ["1:00 PM", "4:00 PM", "7:00 PM"] },
        { id: 3, name: "Interstellar", showtimes: ["2:00 PM", "5:00 PM", "8:00 PM"] },
        { id: 4, name: "Avengers: Endgame", showtimes: ["11:00 AM", "2:30 PM", "6:30 PM"] },
        { id: 5, name: "The Dark Knight", showtimes: ["12:30 PM", "3:30 PM", "7:30 PM"] },
    ];

    const movieList = document.getElementById('movie-list');
    const seatMap = document.getElementById('seat-map');
    const confirmButton = document.getElementById('confirm-selection-button');
    const paymentForm = document.getElementById('payment-form');

    const generateSeats = () => {
        seatMap.innerHTML = '';
        for (let i = 0; i < 100; i++) {
            const seat = document.createElement('div');
            seat.classList.add('seat');
            seat.addEventListener('click', () => {
                if (!seat.classList.contains('taken')) {
                    seat.classList.toggle('selected');
                }
            });
            seatMap.appendChild(seat);
        }
    };

    const loadMovies = () => {
        movieList.innerHTML = movies.map(movie => `
            <div class="movie-card">
                <h3>${movie.name}</h3>
                <p>Showtimes: ${movie.showtimes.join(", ")}</p>
                <button onclick="selectMovie(${movie.id})">Select</button>
            </div>
        `).join('');
    };

    window.selectMovie = (id) => {
        alert(`Selected movie: ${movies.find(movie => movie.id === id).name}`);
        document.getElementById('seat-selection-section').classList.remove('hidden');
        generateSeats();
    };

    confirmButton.addEventListener('click', () => {
        document.getElementById('payment-section').classList.remove('hidden');
    });

    paymentForm.addEventListener('submit', (event) => {
        event.preventDefault();
        alert('Payment Successful! Your ticket is confirmed.');
    });

    loadMovies();
});
