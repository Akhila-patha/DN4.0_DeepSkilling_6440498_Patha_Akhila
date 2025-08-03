import React, { useState } from 'react';

const CurrencyConvertor = () => {
  const [amount, setAmount] = useState('');
  const [currency, setCurrency] = useState('Euro');

  const exchangeRates = {
    Euro: 80,
    USD: 75,
    Yen: 0.65,
    GBP: 95
  };

  const handleSubmit = (e) => {
  e.preventDefault();
  if (!amount || isNaN(amount)) {
    alert('Please enter a valid amount in rupees');
    return;
  }

  const rate = exchangeRates[currency];
  const converted = (parseFloat(amount) / rate).toFixed(2); // FIXED
  alert(`Converting to ${currency} Amount is ${converted}`);
};


  const formStyle = {
    width: '300px',
    margin: '0 auto',
    textAlign: 'left',
    fontSize: '16px'
  };

  const labelStyle = {
    display: 'block',
    marginTop: '10px',
    marginBottom: '5px',
    fontWeight: 'bold'
  };

  const inputStyle = {
    width: '100%',
    padding: '5px',
    marginBottom: '10px'
  };

  const buttonStyle = {
    width: '100%',
    padding: '8px',
    fontWeight: 'bold',
    backgroundColor: '#f0f0f0',
    border: '1px solid #ccc',
    cursor: 'pointer'
  };

  const headingStyle = {
    color: 'green',
    fontWeight: 'bold',
    textAlign: 'center',
    fontSize: '28px',
    marginBottom: '20px'
  };

  return (
    <div>
      <h2 style={headingStyle}>Currency Convertor!!!</h2>
      <form onSubmit={handleSubmit} style={formStyle}>
        <label style={labelStyle}>Amount:</label>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          style={inputStyle}
          placeholder="Enter amount in ₹"
          required
        />

        <label style={labelStyle}>Currency:</label>
        <select
          value={currency}
          onChange={(e) => setCurrency(e.target.value)}
          style={inputStyle}
        >
          <option value="Euro">Euro</option>
          <option value="USD">USD</option>
          <option value="Yen">Yen</option>
          <option value="GBP">British Pound</option>
        </select>

        <button type="submit" style={buttonStyle}>Submit</button>
      </form>
    </div>
  );
};

export default CurrencyConvertor;
