// Cart.js
import React from "react";

class Cart extends React.Component {
  render() {
    return (
      <div style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
        <h3>{this.props.Itemname}</h3>
        <p>Price: ₹{this.props.Price}</p>
      </div>
    );
  }
}

// Default Props
Cart.defaultProps = {
  Itemname: "Unknown Item",
  Price: 0
};

export default Cart;
