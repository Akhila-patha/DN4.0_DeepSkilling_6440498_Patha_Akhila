import React, { useState } from 'react';

function Register() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const validate = () => {
    const { name, email, password } = formData;

    if (name.trim().length < 5) {
      alert('Full Name must be 5 characters long!');
      return false;
    }

    if (!email.includes('@') || !email.includes('.')) {
      alert('Email is not valid!');
      return false;
    }

    if (password.length < 8) {
      alert('Password must be 8 characters long!');
      return false;
    }

    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (validate()) {
      alert('Registration successful!');
      setFormData({
        name: '',
        email: '',
        password: '',
      });
    }
  };

  return (
    <div style={{ paddingTop: '40px', textAlign: 'center' }}>
      <h2 style={{ color: 'red', fontWeight: 'bold' }}>Register Here!!!</h2>

      <form onSubmit={handleSubmit} style={{ display: 'inline-block', textAlign: 'left', marginTop: '20px' }}>
        <div style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
          <label style={{ width: '100px' }}><strong>Name:</strong></label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            style={{ width: '250px', padding: '5px' }}
            required
          />
        </div>

        <div style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
          <label style={{ width: '100px' }}><strong>Email:</strong></label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            style={{ width: '250px', padding: '5px' }}
            required
          />
        </div>

        <div style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
          <label style={{ width: '100px' }}><strong>Password:</strong></label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            style={{ width: '250px', padding: '5px' }}
            required
          />
        </div>

        <div style={{ textAlign: 'center' }}>
          <button type="submit" style={{ padding: '6px 16px' }}>Submit</button>
        </div>
      </form>
    </div>
  );
}

export default Register;
