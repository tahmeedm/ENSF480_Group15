import React, { useState } from 'react';
import SearchBar from './SearchBar.tsx';

function MovieSelection({ movies, currentTheater, onMovieSelect, onBack }) {
    // Initial filter of movies based on the selected theater's id
    const [filteredMovies, setFilteredMovies] = useState(
        movies.filter(movie => movie.theaterId === currentTheater.id)
    );

    // Handle movie selection
    const handleMovieClick = (movie) => {
        onMovieSelect(movie); // Notify parent about the selected movie
    };

    const handleBackClick = () => {
        onBack(null); // Reset the current theater
    };

    // Handle search query input and filter movies
    const handleSearch = (query) => {
        const filtered = movies.filter((movie) =>
            movie.name.toLowerCase().includes(query.toLowerCase()) &&
            movie.theaterId === currentTheater.id // Only filter within the selected theater
        );
        setFilteredMovies(filtered);
    };

    return (
        <section id="movies-section">
            <button onClick={handleBackClick}>Back</button> {/* Back button to reset the current theater */}
            <h2>Available Movies at {currentTheater.name}</h2>
            <SearchBar onSearch={handleSearch} />
            <div id="movie-list">
                {/* If there are no movies for the selected theater */}
                {filteredMovies.length === 0 ? (
                    <p>No movies available for this theater at the moment.</p>
                ) : (
                    // Render each filtered movie
                    filteredMovies.map((movie) => (
                        <div
                            key={movie.id}
                            className="movie-card"
                            onClick={() => handleMovieClick(movie)}
                        >
                            <h3>{movie.name}</h3>
                            <p>Showtimes: {movie.showtimes.join(', ')}</p>
                        </div>
                    ))
                )}
            </div>
        </section>
    );
}

export default MovieSelection;
