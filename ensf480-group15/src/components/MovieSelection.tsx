import React, { useState, useEffect } from 'react';
import SearchBar from './SearchBar.tsx';

// Function to group screenings by movie and theater
const groupScreeningsByMovieAndTheater = (screenings, currentTheater) => {
    const grouped = {};

    screenings.forEach(screening => {
        console.log('Processing screening:', screening); // Log each screening
        if (screening.theatre.id === currentTheater.id) {
            const { movie, screenDate } = screening;
            const key = `${movie.id}-${screening.theatre.id}`;

            if (!grouped[key]) {
                grouped[key] = {
                    movie,
                    showtimes: [],
                };
            }

            grouped[key].showtimes.push(screenDate);
        }
    });

    return Object.values(grouped).map(({ movie, showtimes }) => ({
        ...movie,
        showtimes: [...new Set(showtimes)].sort((a, b) => new Date(a) - new Date(b))
    }));
};

function MovieSelection({ Screenings, currentTheater, onMovieSelect, onBack }) {
    const [filteredMovies, setFilteredMovies] = useState([]);
    const [allMovies, setAllMovies] = useState([]);  // State for the original movie list
    const [loading, setLoading] = useState(true);  // State for loading
    const [error, setError] = useState(null);      // State for error handling

    useEffect(() => {
        console.log('Screenings:', Screenings);
        console.log('Current Theater:', currentTheater);

        try {
            const response = await axios.get(`http://localhost:8083/screenings/theatre/${currentTheater.id}`);
            console.log('Response data:', response.data);
            const groupedMovies = groupScreeningsByMovieAndTheater(response.data);
            setAllMovies(groupedMovies);  // Save the original list of movies
            setFilteredMovies(groupedMovies);  // Initialize filtered list
        } catch (err) {
            console.error('Error fetching screenings:', err);
            setError('Failed to load screenings. Please try again later.');
        } finally {
            setLoading(false);
        }
    };

    fetchScreenings();
}, [currentTheater]);

const handleMovieClick = (movie) => {
    onMovieSelect(movie);
};

const handleBackClick = () => {
    onBack(null);
};

const handleSearch = (query) => {
    if (query === '') {
        setFilteredMovies(allMovies);  // If the search is cleared, reset to all movies
    } else {
        const filtered = allMovies.filter((movie) =>
            movie.name.toLowerCase().includes(query.toLowerCase())
        );
        setFilteredMovies(filtered);  // Set filtered list based on the query
    }
};

if (loading) {
    return <p>Loading screenings...</p>;
}

if (error) {
    return <p>{error}</p>;
}

return (
    <section id="Screenings-section">
        <button onClick={handleBackClick}>Back</button>
        <h2>Available Movies at {currentTheater.theatreName}</h2>
        <SearchBar onSearch={handleSearch} />
        <div id="movie-list">
            {filteredMovies.length === 0 ? (
                <p>No movies available for this theater at the moment.</p>
            ) : (
                filteredMovies.map((movie) => (
                    <div
                        key={movie.id}
                        className="movie-card"
                        onClick={() => handleMovieClick(movie)}
                    >
                        <h3>{movie.name}</h3>
                        <p>{movie.description}</p>
                        <p>Release Date: {movie.releaseDate}</p>
                        <p>Showtimes: {movie.showtimes.join(', ')}</p>
                    </div>
                ))
            )}
        </div>
    </section>
);
}

export default MovieSelection;
