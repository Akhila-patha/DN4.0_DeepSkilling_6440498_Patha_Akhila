import React from 'react';

const SyntheticEventButton = () => {
  const handleClick = (event) => {
    alert('I was clicked');
    console.log('Synthetic event:', event);
  };

  return (
    <div>
      <h2>Synthetic Event Example</h2>
      <button onClick={handleClick}>Click Me</button>
    </div>
  );
};

export default SyntheticEventButton;
