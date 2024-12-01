import React, { useState, useEffect, useCallback } from 'react';
import SignSelection from './components/SignSelection.tsx';
import SignInForm from './components/SignInForm.tsx';
import SignUpForm from './components/SignUpForm.tsx';
import UserSelection from './components/UserSelection.tsx';
import TheaterSelect from './components/TheaterSelect.tsx';
import MovieSelection from './components/MovieSelection.tsx';
import SeatForm from './components/SeatForm.tsx';
import PaymentForm from './components/PaymentForm.tsx';
import ReceiptDisplay from './components/ReceiptDisplay.tsx';
import ShowtimeSelection from './components/ShowtimeSelection.tsx';
import BookedTickets from './components/BookedTickets.tsx';
import GuestCheckin from './components/GuestCheckin.tsx';

//fetchTheatres
const FetchTheatres = ({ onDataFetched }) => {
  useEffect(() => {
    const apiEndpoint = 'http://localhost:8083/theatres';

    const fetchTheatres = async () => {
      try {
        const response = await fetch(apiEndpoint);

        // Check if the response is okay (status 2xx)
        if (!response.ok) {
          throw new Error(`Error: ${response.statusText}`);
        }

        const data = await response.json();
        console.log('Fetched Theatres:', data);

        // Pass data to the parent component
        onDataFetched(data);
      } catch (error) {
        console.error('Error fetching theatres:', error);
        // Optional: You could also handle errors by setting state and displaying a message to users
      }
    };

    fetchTheatres();

    // Cleanup function (optional): This ensures that if the component unmounts before the fetch completes, 
    // the request doesn't try to update state on an unmounted component.
    return () => {
      console.log('Cleaning up FetchTheatres effect.');
    };
  }, [onDataFetched]); // Dependency array ensures the effect runs only when onDataFetched changes

  return null; // This component doesn't need to render anything directly
};

const App = () => {
  const [isRegisteredUser, setIsRegisteredUser] = useState(null);
  const [isSignUp, setSignUpIn] = useState(null);
  const [isSignedIn, setSignedIn] = useState(false);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [currentMovie, setCurrentMovie] = useState(null);
  const [currentTheater, setCurrentTheater] = useState(null);
  const [purchasedTicket, setPurchasedTicket] = useState(null);
  const [seats, setSeats] = useState([]);  // Seats state, initialized as empty array
  const [totalPrice, setTotalPrice] = useState(0);
  const [showTime, setShowTime] = useState(null);

  const [theaters, setTheaters] = useState([]);
  const [selectedScreening, setSelectedScreening] = useState(null);
  const [screenings, setScreenings] = useState([]);
  const [userInfo, setUserInfo] = useState(null);
  const [paymentInfo, setPaymentInfo] = useState(null);

  // Memoize the handleScreenFetched function to prevent unnecessary re-renders
  const handleScreenFetched = useCallback((data) => {
    console.log('Data fetched:', data);
    setScreenings(data); // Update screenings state with fetched data
  }, []);

  const handleTheaterFetched = useCallback((data) => {
    console.log('Data fetched:', data);
    setTheaters(data); // Update theaters state with fetched data
  }, []);

  const handleUserSelection = (isRegistered) => {
    setIsRegisteredUser(isRegistered);
  };

  const handleSignSelection = (isSignUp) => {
    setSignUpIn(isSignUp);
  };

  const handlePriceChange = (price) => {
    setTotalPrice(price);
  };

  const handleSignIn = (isSignedIn, userInfo) => {
    setSignedIn(isSignedIn);

    console.log('Signed In', userInfo);
    setUserInfo(userInfo); // Store the user info in state
    setPaymentInfo(userInfo.paymentInfo); // Store payment info separately
  };

  const handleTheaterSelection = (theater) => {
    setCurrentMovie(null);
    setCurrentTheater(theater);
  };

  const handleMovieSelection = (movie) => {
    console.log('Selected movie:', movie);
    setCurrentMovie(movie);
  };

  const handleSeatSelection = (seats) => {
    setSelectedSeats(seats);
  };

  const handleTicketPurchase = (ticketDetails) => {
    setPurchasedTicket(ticketDetails);
  };

  const handleShowtimeSelection = (showtime) => {
    setShowTime(showtime);
  };

  // Find selected screening based on currentMovie, currentTheater, and showTime
  const findSelectedScreening = () => {
    return screenings.find((screening) => {
      return (
        screening.movie.name === currentMovie?.name &&
        screening.theatre.theatreName === currentTheater?.theatreName &&
        screening.screenDate === showTime
      );
    });
  }

  // useEffect to call findSelectedScreening when currentMovie, currentTheater, and showTime are set
  useEffect(() => {
    if (currentMovie && currentTheater && showTime) {
      const selected = findSelectedScreening();
      setSelectedScreening(selected); // Update the selected screening state
      console.log('Selected Screening:', selected);
    }
  }, [currentMovie, currentTheater, showTime, screenings]); // Re-run whenever any of these values change

  // Return to Main menu
  const resetFields = () => {
    setSelectedSeats([]);
    setCurrentMovie(null);
    setCurrentTheater(null);
    setPurchasedTicket(null);
  };

  const signOut = () => {
    setSignedIn(false);
    setUserInfo(null);
    setIsRegisteredUser(null);
  }

  const handleGuestCheckin = () => {
    setSignedIn(true);
  }

  return (
    <div className="App">
      <FetchTheatres onDataFetched={handleTheaterFetched} />
      <header onClick={resetFields}>
        <h1>AcmePlex Theater Ticket Reservation</h1>
      </header>

      <div className='appContent'>
        {isSignedIn === false && (isRegisteredUser === true || isRegisteredUser === false) && <button onClick={signOut}>Back</button>}
        {isRegisteredUser === null && userInfo === null && <UserSelection onUserSelect={handleUserSelection} />}
        {isRegisteredUser === false && !isSignedIn && <GuestCheckin onGuestCheckin={handleGuestCheckin} />}
        {isRegisteredUser && !isSignedIn && <SignSelection onUserSelect={handleSignSelection} />}
        {isRegisteredUser && isSignUp === false && !currentTheater && !isSignedIn && <SignInForm onUserSelect={handleSignIn} />}
        {isRegisteredUser && isSignUp === true && !currentTheater && !isSignedIn && <SignUpForm onUserSelect={handleSignIn} />}
        {(isSignedIn || userInfo) && !currentTheater &&
          (
            <div>
              <button onClick={signOut}>Sign Out</button>
              <h2>Welcome {userInfo.name}</h2>
              <div className='MainMenu'>
                <TheaterSelect onTheaterSelect={handleTheaterSelection} theaters={theaters} />
                <BookedTickets User={userInfo} />
              </div>
            </div>
          )
        }
        {currentTheater && selectedSeats.length === 0 && (
          <MovieSelection
            currentTheater={currentTheater}
            onMovieSelect={handleMovieSelection}
            onBack={handleTheaterSelection}  // Passing the handleBack function to MovieSelection
          />
        )}
        {currentMovie && selectedSeats.length === 0 && (
          <div>
            <ShowtimeSelection
              Showtimes={currentMovie.showtimes}
              selectShowtime={handleShowtimeSelection}
              selectedShowtime={showTime}
            />
            <SeatForm
              onSeatSelect={handleSeatSelection}
              setGlobalSeats={setSeats}
              seatingArrangement={seats}
              priceHandler={handlePriceChange}
              selectedScreening={selectedScreening}
            />
          </div>
        )}
        {selectedSeats.length > 0 && !purchasedTicket &&
          <PaymentForm
            onPurchase={handleTicketPurchase}
            setPaymentInfo={setPaymentInfo}
            paymentInfo={paymentInfo}
            selectedSeats={selectedSeats}
            currentMovie={currentMovie}
            currentTheater={currentTheater}
            seatingArrangement={seats}
            price={totalPrice}
            screenDate={showTime}
          />
        }
        {purchasedTicket &&
          <ReceiptDisplay ticket={purchasedTicket} />
        }
      </div>
    </div>
  );
};

export default App;
