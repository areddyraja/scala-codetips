package com.akrantha.akkasamples


import akka.actor.{ActorSystem, Props}  
import akka.actor.Actor  
import akka.actor.ActorLogging  
import akka.actor.Kill

/**
 * @author reddyraja
 */
object ActorKilledExceptionApp  extends App{

  val actorSystem=ActorSystem("ActorKilledExceptionSystem")
  val actor=actorSystem.actorOf(Props[ActorKilledExceptionActor])
  actor!"something"
  actor!Kill
  actor!"something else that falls into dead letter queue"
}

class ActorKilledExceptionActor extends Actor with ActorLogging{  
  def receive={
    case message:String=> log.info (message)
  }
}
