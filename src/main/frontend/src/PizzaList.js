import React from "react";

const PizzaList = ({pizzas}) => (
    <ul id="pizzaList">
        {pizzas && pizzas.map(pizza => <PizzaDetail key={pizza.name} name={pizza.name} />)}
    </ul>
);

const PizzaDetail = (pizza) => (
    <li>{pizza.name}</li>
);

export default PizzaList