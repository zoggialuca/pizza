import React, { useEffect, useState } from "react";
import PizzaList from "./PizzaList";
import {throwErrorIfResponseNotOk} from "./util/error.js";

const Pizzas = () => {
    const [pizzas, setPizzas] = useState();
    
    const fetchPizza = () => {
        fetch("http://localhost:3001/pizzas")
           .then(res => throwErrorIfResponseNotOk(res, Error("No answer from backend")))
           .then(res => res.json())
           .then(json => setPizzas(json))
           .catch(e => console.log(`ERROR\n${e}`));
    };
    useEffect(fetchPizza, []);

    return (
        <div id="pizzas_list">
            <p>List of pizzas</p>
            <PizzaList pizzas={pizzas} />
        </div>
    );
}

export default Pizzas