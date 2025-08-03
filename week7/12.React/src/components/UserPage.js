import React from 'react';
import FlightList from './FlightList';

const UserPage = () => {
  return (
    <div>
      <h2>Welcome User</h2>
      <FlightList showBookingButton={true} />
    </div>
  );
};

export default UserPage;
