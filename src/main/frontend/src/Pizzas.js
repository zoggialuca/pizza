import React, { useEffect, useState } from "react";
import PizzaList from "./PizzaList";
import {throwErrorIfResponseNotOk} from "./util/error.js";

const BACKEND_API_URL = process.env.REACT_APP_BACKEND_API_URL

const Pizzas = () => {
    const [pizzas, setPizzas] = useState();
    
    const fetchPizza = () => {
        fetch(`${BACKEND_API_URL}pizzas`)
           .then(res => throwErrorIfResponseNotOk(res, Error("No answer from backend")))
           .then(res => res.json())
           .then(json => setPizzas(json._embedded.pizzaList))
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