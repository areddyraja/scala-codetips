package com.akrantha.akkasamples

import akka.actor._

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case "what"  => println("I am Akka sample")
    case _       => println("huh?")
  }
}

case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

class HelloActorwithName(myName: String) extends Actor {
  def receive = {
    case "hello" => println("hello from %s".format(myName))
    case "what"  => println("I am Akka sample")
    case _       => println("huh?")
  }
}
class Ping(pong: ActorRef) extends Actor {
  var count = 0
  def incrementAndPrint { count += 1; println("ping") }
  def receive = {
    case StartMessage =>
      println("Start")
      pong ! PingMessage
    case PongMessage =>
      incrementAndPrint
      if (count > 99) {
        sender ! StopMessage
        println("Stop Message from Pong")
        context.stop(self)
      } else {
        sender ! PingMessage
      }
  }
}
class Pong extends Actor {
  def receive = {
    case PingMessage =>
      println("Pong")
      sender ! PongMessage

    case StopMessage =>
      println("Stop message from Ping")
      context
        .stop(self)
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  val helloActorWithName = system.actorOf(Props(new HelloActorwithName("Reddy")), name = "helloactorwithname")

  helloActor ! "hello"
  helloActor ! "buenos dias"
  helloActor ! "what"

  helloActorWithName ! "hello"
  helloActorWithName ! "what"

  val pong = system.actorOf(Props[Pong], name = "pong")
  val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
  ping ! StartMessage

}
