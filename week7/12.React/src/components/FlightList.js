import React from 'react';

const flights = [
  { id: 1, flight: 'Indigo 6E-123', from: 'Delhi', to: 'Mumbai', time: '10:00 AM' },
  { id: 2, flight: 'Air India AI-456', from: 'Bangalore', to: 'Chennai', time: '2:30 PM' },
  { id: 3, flight: 'SpiceJet SG-789', from: 'Kolkata', to: 'Delhi', time: '6:45 PM' },
];

const FlightList = ({ showBookingButton }) => {
  const handleBooking = (flight) => {
    alert(`Ticket booked for ${flight.flight} from ${flight.from} to ${flight.to}`);
  };

  return (
    <div>
      <h3>Flight Details</h3>
      <ul>
        {flights.map(flight => (
          <li key={flight.id}>
            ✈ {flight.flight} - {flight.from} to {flight.to} at {flight.time}
            {showBookingButton && (
              <button onClick={() => handleBooking(flight)} style={{ marginLeft: '10px' }}>
                Book Ticket
              </button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FlightList;
