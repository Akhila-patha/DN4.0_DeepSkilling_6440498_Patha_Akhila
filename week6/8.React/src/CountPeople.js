import React from "react";

class CountPeople extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      entryCount: 0,
      exitCount: 0
    };
  }

  // Method to update entry count
  UpdateEntry = () => {
    this.setState((prevState) => ({
      entryCount: prevState.entryCount + 1
    }));
  };

  // Method to update exit count
  UpdateExit = () => {
    this.setState((prevState) => ({
      exitCount: prevState.exitCount + 1
    }));
  };

  render() {
    return (
      <div style={{ textAlign: "center", marginTop: "50px" }}>
        <h1>Mall Entry & Exit Counter</h1>
        <p>Number of People Entered: {this.state.entryCount}</p>
        <p>Number of People Exited: {this.state.exitCount}</p>

        <button
          onClick={this.UpdateEntry}
          style={{
            padding: "10px 20px",
            marginRight: "10px",
            backgroundColor: "green",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer"
          }}
        >
          Login
        </button>

        <button
          onClick={this.UpdateExit}
          style={{
            padding: "10px 20px",
            backgroundColor: "red",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer"
          }}
        >
          Exit
        </button>
      </div>
    );
  }
}

export default CountPeople;
