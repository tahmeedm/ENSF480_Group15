// import React, { useState, useEffect } from 'react';

// const FetchScreenings = () => {
//     const [screenings, setScreenings] = useState([]);

//     useEffect(() => {
//         // Replace with your API endpoint
//         const apiEndpoint = 'http://localhost:8083/fetchScreenings';

//         const fetchScreenings = async () => {
//             try {
//                 const response = await fetch(apiEndpoint);
//                 const data = await response.json();
//                 console.log(data);
//                 setScreenings(data);
//             } catch (error) {
//                 console.error('Error fetching movies:', error);
//             }
//         };

//         fetchScreenings();
//     }, []);

//     return screenings;
// };

// export default FetchScreenings;
