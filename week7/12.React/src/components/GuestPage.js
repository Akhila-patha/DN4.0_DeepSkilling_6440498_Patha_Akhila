import React from 'react';
import FlightList from './FlightList';

const GuestPage = () => {
  return (
    <div>
      <h2>Welcome Guest</h2>
      <p>Please login to book tickets.</p>
      <FlightList showBookingButton={false} />
    </div>
  );
};

export default GuestPage;
