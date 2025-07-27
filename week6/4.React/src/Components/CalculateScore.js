import React from "react";
import "../Stylesheets/mystyle.css";

function CalculateScore(props) {
  const { name, school, total, goal } = props;
  const percentage = (total / goal).toFixed(2); // Calculate percentage

  return (
    <div className="student-details">
      <h1>Student Details:</h1>
      <p><span className="label name">Name:</span> {name}</p>
      <p><span className="label school">School:</span> {school}</p>
      <p><span className="label total">Total:</span> {total} Marks</p>
      <p><span className="label score">Score:</span> {percentage}%</p>
    </div>
  );
}

export default CalculateScore;
