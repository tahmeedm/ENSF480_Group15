import React, { useState } from 'react';
import SignSelection from './components/SignSelection.tsx';
import SignInForm from './components/SignInForm.tsx';
import SignUpForm from './components/SignUpForm.tsx';
import UserSelection from './components/UserSelection.tsx';
import TheaterSelect from './components/TheaterSelect.tsx';
import MovieSelection from './components/MovieSelection.tsx';
import SeatForm from './components/SeatForm.tsx';
import PaymentForm from './components/PaymentForm.tsx';
import ReceiptDisplay from './components/ReceiptDisplay.tsx';

const App = () => {
  const [isRegisteredUser, setIsRegisteredUser] = useState(null);
  const [isSignUp, setSignUpIn] = useState(null);
  const [isSignedIn, setSignedIn] = useState(false);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [currentMovie, setCurrentMovie] = useState(null);
  const [currentTheater, setCurrentTheater] = useState(null);
  const [purchasedTicket, setPurchasedTicket] = useState(null);
  const totalSeats = 100;
  const TICKET_COST = 15;

  const [theaters, setTheaters] = useState([
    { id: 1, name: "AcmePlex Downtown" },
    { id: 2, name: "AcmePlex Mall" },
    { id: 3, name: "AcmePlex Suburbs" },
  ]);

  const [movies, setMovies] = useState([
    { id: 1, name: 'Inception', theaterId: 1, showtimes: ['12:00 PM', '3:00 PM', '6:00 PM'] },
    { id: 2, name: 'The Matrix', theaterId: 2, showtimes: ['1:00 PM', '4:00 PM', '7:00 PM'] },
    { id: 3, name: 'Interstellar', theaterId: 3, showtimes: ['2:00 PM', '5:00 PM', '8:00 PM'] },
    { id: 4, name: 'Avengers: Endgame', theaterId: 1, showtimes: ['11:00 AM', '2:30 PM', '6:30 PM'] },
    { id: 5, name: 'The Dark Knight', theaterId: 2, showtimes: ['12:30 PM', '3:30 PM', '7:30 PM'] },
  ]);

  const [paymentInfo, setPaymentInfo] = useState(null);

  const [filteredMovies, setFilteredMovies] = useState(movies);

  const handleUserSelection = (isRegistered) => {
    setIsRegisteredUser(isRegistered);
  };

  const handleSignSelection = (isSignUp) => {
    setSignUpIn(isSignUp);
  };

  const handleSignIn = (isSignedIn) => {
    setSignedIn(isSignedIn);
  };

  const handleTheaterSelection = (theater) => {
    setCurrentTheater(theater);
  };

  const handleMovieSelection = (movie) => {
    setCurrentMovie(movie);
  };

  const handleSeatSelection = (seats) => {
    setSelectedSeats(seats);
  };

  const handleTicketPurchase = (ticketDetails) => {
    setPurchasedTicket(ticketDetails);
  };

  // Function to reset all fields
  const resetFields = () => {
    setIsRegisteredUser(null);
    setSignUpIn(false);
    setSignedIn(false);
    setSelectedSeats([]);
    setCurrentMovie(null);
    setCurrentTheater(null);
    setPurchasedTicket(null);
    setPaymentInfo(null);
  };


  return (
    <div className="App">
      <header onClick={resetFields}>
        <h1>AcmePlex Theater Ticket Reservation</h1>
      </header>
      <div className='appContent'>
        {isRegisteredUser === null && <UserSelection onUserSelect={handleUserSelection} />}
        {isRegisteredUser && !isSignedIn && <SignSelection onUserSelect={handleSignSelection} />}
        {isRegisteredUser && isSignUp === false && !currentTheater && !isSignedIn && <SignInForm onUserSelect={handleSignIn} setPaymentInfo={setPaymentInfo} />}
        {isRegisteredUser && isSignUp === true && !currentTheater && !isSignedIn && <SignUpForm />}
        {(isRegisteredUser == false || isSignedIn) && !currentTheater && <TheaterSelect
          onTheaterSelect={handleTheaterSelection} theaters={theaters} />}
        {currentTheater && selectedSeats.length === 0 && (
          <MovieSelection
            movies={movies}
            currentTheater={currentTheater}
            onMovieSelect={handleMovieSelection}
            onBack={handleTheaterSelection}  // Passing the handleBack function to MovieSelection
          />
        )}
        {currentMovie && selectedSeats.length === 0 && <SeatForm onSeatSelect={handleSeatSelection} SEAT_COST={TICKET_COST} />}
        {selectedSeats.length > 0 && !purchasedTicket && <PaymentForm
          onPurchase={handleTicketPurchase}
          setPaymentInfo={setPaymentInfo}
          paymentInfo={paymentInfo}
          selectedSeats={selectedSeats}
          seatCost={TICKET_COST}
          currentMovie={currentMovie}
          currentTheater={currentTheater}
        />}
        {purchasedTicket && <ReceiptDisplay ticket={purchasedTicket} />}
      </div>
    </div>
  );
};

export default App;
