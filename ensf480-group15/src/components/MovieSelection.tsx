import React, { useState, useEffect } from 'react';
import axios from 'axios';
import SearchBar from './SearchBar.tsx';

// Function to group screenings by movie and theater
const groupScreeningsByMovieAndTheater = (screenings) => {
    const grouped = {};

    screenings.forEach(screening => {
        const { movie, screenDate, theatre } = screening;
        const key = `${movie.id}-${theatre.id}`;

        if (!grouped[key]) {
            grouped[key] = {
                movie,
                showtimes: [],
            };
        }

        grouped[key].showtimes.push(screenDate);
    });

    // Return grouped movies with sorted and unique showtimes
    return Object.values(grouped).map(({ movie, showtimes }) => ({
        ...movie,
        showtimes: [...new Set(showtimes)].sort((a, b) => new Date(a) - new Date(b))
    }));
};

function MovieSelection({ currentTheater, onMovieSelect, onBack, setScreenings }) {
    const [filteredMovies, setFilteredMovies] = useState([]);
    const [loading, setLoading] = useState(true);  // State for loading
    const [error, setError] = useState(null);      // State for error handling

    useEffect(() => {
        if (!currentTheater?.id) return;  // Make sure we have a valid theater ID

        const fetchScreenings = async () => {
            setLoading(true);
            setError(null);  // Reset error state

            try {
                const response = await axios.get(`http://localhost:8083/screenings/theatre/${currentTheater.id}`);
                console.log('Response data:', response.data);
                const groupedMovies = groupScreeningsByMovieAndTheater(response.data);
                setFilteredMovies(groupedMovies);
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
        const filtered = filteredMovies.filter((movie) =>
            movie.name.toLowerCase().includes(query.toLowerCase())
        );
        setFilteredMovies(filtered);
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
