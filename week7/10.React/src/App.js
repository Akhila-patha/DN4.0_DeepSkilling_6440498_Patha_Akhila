import React from "react";
import "./App.css";

function App() {
  // Single office object
  const office = {
    name: "Green Tower",
    rent: 55000,
    address: "Plot No 12, Tech Park, Bangalore",
    image: "images/img1.jpg" // relative to public folder
  };

  // List of office spaces
  const officeList = [
    {
      name: "Tech Park One",
      rent: 50000,
      address: "MG Road, Pune",
      image: "images/img2.jpg"
    },
    {
      name: "Skyline Corporate",
      rent: 75000,
      address: "Salt Lake, Kolkata",
      image: "images/img3.jpg"
    }
  ];

  return (
    <div className="App">
      <h1>Office Space Rental App</h1>

      <h2>Featured Office</h2>
      <img src={office.image} alt={office.name} width="300" />
      <p><strong>Name:</strong> {office.name}</p>
      <p><strong>Address:</strong> {office.address}</p>
      <p>
        <strong>Rent:</strong>{" "}
        <span style={{ color: office.rent < 60000 ? "red" : "green" }}>
          ₹{office.rent}
        </span>
      </p>

      <hr />

      <h2>Available Office Spaces</h2>
      {officeList.map((item, index) => (
        <div key={index} className="office-card">
          <img src={item.image} alt={item.name} width="300" />
          <p><strong>Name:</strong> {item.name}</p>
          <p><strong>Address:</strong> {item.address}</p>
          <p>
            <strong>Rent:</strong>{" "}
            <span style={{ color: item.rent < 60000 ? "red" : "green" }}>
              ₹{item.rent}
            </span>
          </p>
        </div>
      ))}
    </div>
  );
}

export default App;
