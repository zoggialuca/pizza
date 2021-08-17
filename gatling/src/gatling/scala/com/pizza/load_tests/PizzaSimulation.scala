package com.pizza.load_tests

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.util.Random

class PizzaSimulation extends Simulation {

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

  setUp(
    pizzaScenario.inject(
      nothingFor(2.seconds),
      atOnceUsers(100),
      rampUsers(200).during(10.seconds),
      constantUsersPerSec(10).during(20.seconds),
      constantUsersPerSec(10).during(20.seconds).randomized,
      rampUsersPerSec(30).to(50).during(20.seconds))
  )
    .assertions(global.responseTime.max.lte(3000), global.failedRequests.percent.is(0))
    .protocols(httpProtocol)
    .maxDuration(3.minutes)
}
