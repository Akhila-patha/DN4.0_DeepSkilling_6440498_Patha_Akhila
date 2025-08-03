import React, { useState } from 'react';

function ComplaintRegister() {
  const [employeeName, setEmployeeName] = useState('');
  const [complaint, setComplaint] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    const transactionId = Math.floor(Math.random() * 1000);

    alert(`Thanks ${employeeName}!\nYour Complaint was Submitted.\nTransaction ID is: ${transactionId}`);

    setEmployeeName('');
    setComplaint('');
  };

  return (
    <div style={{ textAlign: 'center', paddingTop: '40px' }}>
      <h2 style={{ color: 'red', fontWeight: 'bold' }}>
        Register your complaints here!!!
      </h2>

      <form onSubmit={handleSubmit} style={{ display: 'inline-block', marginTop: '30px' }}>
        <div style={{ display: 'flex', alignItems: 'center', marginBottom: '15px' }}>
          <label style={{ width: '100px', textAlign: 'right', marginRight: '10px' }}><strong>Name:</strong></label>
          <input
            type="text"
            value={employeeName}
            onChange={(e) => setEmployeeName(e.target.value)}
            required
            style={{ width: '250px', padding: '6px' }}
          />
        </div>

        <div style={{ display: 'flex', alignItems: 'center', marginBottom: '15px' }}>
          <label style={{ width: '100px', textAlign: 'right', marginRight: '10px' }}><strong>Complaint:</strong></label>
          <textarea
            rows="3"
            value={complaint}
            onChange={(e) => setComplaint(e.target.value)}
            required
            style={{ width: '250px', padding: '6px' }}
          />
        </div>

        <div style={{ textAlign: 'center', marginTop: '10px' }}>
          <button type="submit" style={{ padding: '6px 20px' }}>Submit</button>
        </div>
      </form>
    </div>
  );
}

export default ComplaintRegister;
