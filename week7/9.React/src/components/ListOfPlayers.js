import React from "react";

const ListOfPlayers = () => {
  // ES6 let and const
  const players = [
    { name: "Virat Kohli", score: 89 },
    { name: "Rohit Sharma", score: 95 },
    { name: "KL Rahul", score: 67 },
    { name: "Shubman Gill", score: 58 },
    { name: "Hardik Pandya", score: 76 },
    { name: "Rishabh Pant", score: 45 },
    { name: "Jasprit Bumrah", score: 35 },
    { name: "Mohammed Siraj", score: 55 },
    { name: "Ravindra Jadeja", score: 72 },
    { name: "Kuldeep Yadav", score: 61 },
    { name: "Suryakumar Yadav", score: 80 }
  ];

  // map() and arrow function to list players
  const playerList = players.map((player, index) => (
    <li key={index}>
      {player.name} - {player.score}
    </li>
  ));

  // Filter players scoring below 70
  const lowScorers = players.filter(player => player.score < 70);

  return (
    <div>
      <h2>List of Players</h2>
      <ul>{playerList}</ul>

      <h3>List of Players having scores Less than 70</h3>
      <ul>
        {lowScorers.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListOfPlayers;
