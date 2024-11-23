-- Create tables
CREATE TABLE IF NOT EXISTS Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Movies (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255),
    director VARCHAR(255),
    release_year INT
);

CREATE TABLE IF NOT EXISTS Showtimes (
    showtime_id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT,
    showtime TIMESTAMP,
    theater_id INT,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

CREATE TABLE IF NOT EXISTS Tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    showtime_id INT,
    seats INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (showtime_id) REFERENCES Showtimes(showtime_id)
);

-- Insert sample data
INSERT INTO Users (username, password, email) VALUES
('john_doe', 'password123', 'john@example.com'),
('jane_doe', 'password456', 'jane@example.com');

INSERT INTO Movies (title, genre, director, release_year) VALUES
('Movie A', 'Action', 'Director A', 2023),
('Movie B', 'Comedy', 'Director B', 2023),
('Movie C', 'Drama', 'Director C', 2023);

-- Insert showtimes
INSERT INTO Showtimes (movie_id, showtime, theater_id) VALUES
(1, '2023-11-22 12:00:00', 1),
(2, '2023-11-22 14:00:00', 2),
(3, '2023-11-22 16:00:00', 3);
