import React, { useState } from 'react';

function SearchBar({ onSearch }) {
    const [query, setQuery] = useState('');

    const handleChange = (e) => {
        const { value } = e.target;
        setQuery(value);
        onSearch(value); // Call the onSearch prop whenever the input changes
    };

    return (
        <div id="search-bar">
            <input
                id="movie-search"
                type="text"
                placeholder="Search for a movie by title..."
                value={query}
                onChange={handleChange}
            />
        </div>
    );
}

export default SearchBar;
