package com.pizza.load_tests

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.util.Random

class PizzaSimulationSmall extends Simulation {

  private val newPizzaNameFeeder = Iterator.continually(Map("pizzaName" -> (Random.alphanumeric.take(20).mkString)))

  private val pizzaFeeder = Array(
    Map("name" -> "Margherita"),
    Map("name" -> "Prosciutto"),
    Map("name" -> "Marinara")
  ).random;
  private val pizzaIdFeeder = Array(
    Map("id" -> 1),
    Map("id" -> 2),
    Map("id" -> 3)
  ).random;

  private val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("application/json")

  private val getPizzas = http("Get pizzas").get("/pizzas")
  private val searchPizzaByName = http("Search pizza by name").get("/pizzas?name=${name}")
  private val getPizzaIngredientsByPizzaId = http("Get pizza ingredients by pizzaId").get("/pizzas/${id}/ingredients")
  private val createPizza = http("Create pizza").post("/pizzas").body(StringBody("{\"name\": \"${pizzaName}\"}")).asJson
  private val someGroup = group("CreatePizzaAndSearchPizzaByNameGroup")(feed(newPizzaNameFeeder).exec(createPizza).pause(2.seconds).feed(pizzaFeeder).exec(searchPizzaByName))
  private val pizzaScenario = scenario("Get all pizzas and then search for every pizza and every pizza ingredients")
    .feed(pizzaFeeder)
    .exec(searchPizzaByName)
    .pause(2.seconds)
    .feed(pizzaIdFeeder)
    .exec(getPizzaIngredientsByPizzaId)
    .pause(2.seconds)
    .exec(getPizzas)
    .pause(2.seconds)
    .feed(newPizzaNameFeeder)
    .exec(createPizza)
    .pause(2.seconds)
    .exec(getPizzas)
    .exec(someGroup)

  setUp(
    pizzaScenario.inject(
      atOnceUsers(100))
  )
    .assertions(global.responseTime.max.lte(3000), details("CreatePizzaAndSearchPizzaByNameGroup" / "Search pizza by name").responseTime.max.lte(50), global.failedRequests.percent.is(0))
    .protocols(httpProtocol)
}
