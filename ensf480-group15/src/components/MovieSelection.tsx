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

    useEffect(() => {
        console.log('Screenings:', Screenings);
        console.log('Current Theater:', currentTheater);

        if (Screenings.length > 0) {
            const groupedMovies = groupScreeningsByMovieAndTheater(Screenings, currentTheater);
            console.log('Grouped Movies:', groupedMovies); // Log the grouped movies
            setFilteredMovies(groupedMovies);
        } else {
            console.log('No screenings available');
        }
    }, [Screenings, currentTheater]);

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
