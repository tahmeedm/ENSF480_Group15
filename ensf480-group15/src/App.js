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
    {
      id: 1,
      title: 'Inception',
      description: 'A skilled thief is given a chance to have his criminal record erased if he can successfully perform an inception on a target\'s subconscious.',
      releaseDate: '2010-07-16',
      theaterId: 1,
      showtimes: ['12:00 PM', '3:00 PM', '6:00 PM']
    },
    {
      id: 2,
      title: 'The Matrix',
      description: 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.',
      releaseDate: '1999-03-31',
      theaterId: 2,
      showtimes: ['1:00 PM', '4:00 PM', '7:00 PM']
    },
    {
      id: 3,
      title: 'Interstellar',
      description: 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.',
      releaseDate: '2014-11-07',
      theaterId: 3,
      showtimes: ['2:00 PM', '5:00 PM', '8:00 PM']
    },
    {
      id: 4,
      title: 'Avengers: Endgame',
      description: 'The Avengers assemble once more in order to undo the damage caused by Thanos in the previous film and to restore peace to the universe.',
      releaseDate: '2019-04-26',
      theaterId: 1,
      showtimes: ['11:00 AM', '2:30 PM', '6:30 PM']
    },
    {
      id: 5,
      title: 'The Dark Knight',
      description: 'Batman faces off against the Joker, a criminal mastermind who wants to plunge Gotham City into anarchy.',
      releaseDate: '2008-07-18',
      theaterId: 2,
      showtimes: ['12:30 PM', '3:30 PM', '7:30 PM']
    }
  ]);

  const [userInfo, setUserInfo] = useState(null);

  const [paymentInfo, setPaymentInfo] = useState(null);

  const handleUserSelection = (isRegistered) => {
    setIsRegisteredUser(isRegistered);
  };

  const handleSignSelection = (isSignUp) => {
    setSignUpIn(isSignUp);
  };

  const handleSignIn = (isSignedIn, userInfo) => {
    setSignedIn(isSignedIn);

    console.log('Signed In', userInfo);
    setUserInfo({
      name: userInfo.name,
      email: userInfo.email,
      phoneNumber: userInfo.phoneNumber,
      paymentInfo: userInfo.paymentInfo,
      address: userInfo.address,
      username: userInfo.username,
      password: userInfo.password,
      adminFee: userInfo.adminFee,
      adminExpiryDate: userInfo.adminExpiryDate,
    });

    console.log('Payment Info', userInfo.paymentInfo);
    setPaymentInfo({
      cardNumber: userInfo.paymentInfo.cardNumber,
      expiryDate: userInfo.paymentInfo.expiryDate,
      cvv: userInfo.paymentInfo.cvv
    })
  };

  const handleTheaterSelection = (theater) => {
    console.log(`Selected Theater: ${theater.name}`);
    setCurrentTheater(theater);
  };

  const handleMovieSelection = (movie) => {
    console.log(`Selected Movie: ${movie.title}`);
    setCurrentMovie(movie);
  };

  const handleSeatSelection = (seats) => {
    console.log(`Selected Seats: ${seats}`);
    setSelectedSeats(seats);
  };

  const handleTicketPurchase = (ticketDetails) => {
    console.log(`Ticket Purchased: ${JSON.stringify(ticketDetails)}`);
    setPurchasedTicket(ticketDetails);
  };

  // Function to reset all fields
  const resetFields = () => {
    console.log('Resetting fields');
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
        {isRegisteredUser && isSignUp === false && !currentTheater && !isSignedIn && <SignInForm onUserSelect={handleSignIn} />}
        {isRegisteredUser && isSignUp === true && !currentTheater && !isSignedIn && <SignUpForm />}
        {(isRegisteredUser === false || isSignedIn) && !currentTheater && <TheaterSelect
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
