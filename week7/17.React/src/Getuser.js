import React, { Component } from 'react';

class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
    };
  }

  async componentDidMount() {
    try {
      const response = await fetch('https://api.randomuser.me/');
      const data = await response.json();
      const userData = data.results[0];

      this.setState({
        user: {
          title: userData.name.title,
          firstname: userData.name.first,
          image: userData.picture.large,
        }
      });
    } catch (error) {
      console.error('Error fetching user:', error);
    }
  }

  render() {
    const { user } = this.state;

    return (
      <div style={{ textAlign: 'center', marginTop: '50px' }}>
        <h2 style={{ color: 'green' }}>Random User Info</h2>
        {user ? (
          <div>
            <h3>{user.title} {user.firstname}</h3>
            <img src={user.image} alt="User" style={{ borderRadius: '50%' }} />
          </div>
        ) : (
          <p>Loading user data...</p>
        )}
      </div>
    );
  }
}

export default Getuser;
