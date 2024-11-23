-- Queries for the Movie Theater website

-- Get all movies
SELECT * FROM Movies;

-- Get a movie by its title
SELECT * FROM Movies WHERE title = ?;

-- Get all showtimes for a specific movie
SELECT * FROM Showtimes WHERE movie_id = ?;

-- Insert a new movie
INSERT INTO Movies (title, genre, director, release_year) VALUES (?, ?, ?, ?);

-- Insert a new showtime for a movie
INSERT INTO Showtimes (movie_id, showtime, theater_id) VALUES (?, ?, ?);

-- Get tickets for a specific showtime
SELECT * FROM Tickets WHERE showtime_id = ?;

-- Insert a new ticket booking
INSERT INTO Tickets (user_id, showtime_id, seats) VALUES (?, ?, ?);

-- Check if a user exists (for login)
SELECT * FROM Users WHERE username = ? AND password = ?;

-- Register a new user
INSERT INTO Users (username, password, email) VALUES (?, ?, ?);
