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

// FetchScreenings as a component that fetches and provides data
const FetchScreenings = ({ onDataFetched }) => {
  useEffect(() => {
    const apiEndpoint = 'http://localhost:8083/fetchScreenings';

    const fetchScreenings = async () => {
      try {
        const response = await fetch(apiEndpoint);
        const data = await response.json();
        console.log('Fetched Screenings:', data);
        onDataFetched(data); // Pass data back to parent component
      } catch (error) {
        console.error('Error fetching movies:', error);
      }
    };

    fetchScreenings();
  }, [onDataFetched]); // Ensures this runs only once when the component is mounted

  return null; // No UI for this component, it just fetches data
};

//fetchTheatres
const FetchTheatres = ({ onDataFetched }) => {
  useEffect(() => {
    const apiEndpoint = 'http://localhost:8083/fetchTheatres';

    const fetchTheatres = async () => {
      try {
        const response = await fetch(apiEndpoint);
        const data = await response.json();
        console.log('Fetched Theatres:', data);
        onDataFetched(data); // Pass data back to parent component
      } catch (error) {
        console.error('Error fetching theatres:', error);
      }
    };

    fetchTheatres();
  }, [onDataFetched]); // Ensures this runs only once when the component is mounted

  return null; // No UI for this component, it just fetches data
}

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

  // Reset all fields
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
      <FetchScreenings onDataFetched={handleScreenFetched} />
      <FetchTheatres onDataFetched={handleTheaterFetched} />
      <header onClick={resetFields}>
        <h1>AcmePlex Theater Ticket Reservation</h1>
      </header>

      <div className='appContent'>
        {isRegisteredUser === null && userInfo === null && <UserSelection onUserSelect={handleUserSelection} />}
        {isRegisteredUser && !isSignedIn && <SignSelection onUserSelect={handleSignSelection} />}
        {isRegisteredUser && isSignUp === false && !currentTheater && !isSignedIn && <SignInForm onUserSelect={handleSignIn} />}
        {isRegisteredUser && isSignUp === true && !currentTheater && !isSignedIn && <SignUpForm onUserSelect={handleSignIn} />}
        {(isRegisteredUser === false || isSignedIn || userInfo) && !currentTheater && <TheaterSelect
          onTheaterSelect={handleTheaterSelection} theaters={theaters} />}
        {currentTheater && selectedSeats.length === 0 && (
          <MovieSelection
            Screenings={screenings}
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
