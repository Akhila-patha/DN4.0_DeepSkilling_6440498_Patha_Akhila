// OnlineShopping.js
import React from "react";
import Cart from "./Cart";

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [
        { Itemname: "Laptop", Price: 60000 },
        { Itemname: "Mobile", Price: 25000 },
        { Itemname: "Headphones", Price: 3000 },
        { Itemname: "Smartwatch", Price: 5000 },
        { Itemname: "Keyboard", Price: 1500 }
      ]
    };
  }

  render() {
    return (
      <div style={{ textAlign: "center" }}>
        <h1>Welcome to Online Shopping</h1>
        {this.state.items.map((item, index) => (
          <Cart key={index} Itemname={item.Itemname} Price={item.Price} />
        ))}
      </div>
    );
  }
}

export default OnlineShopping;
