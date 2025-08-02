import React from "react";
import ListOfPlayers from "./components/ListOfPlayers";
import IndianPlayers from "./components/IndianPlayers";

function App() {
  const flag = false; // Change this to false to render <IndianPlayers />

  return (
    <div className="App">
      <h1>Welcome to Cricket App</h1>
      {flag ? <ListOfPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default App;
