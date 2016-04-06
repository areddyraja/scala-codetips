package com.akrantha.akkasamples
import akka.actor.{ActorLogging, Actor}  
import akka.event.LoggingReceive
import akka.actor.{ActorSystem, Props}
import akka.actor.DeadLetter  

/**
 * @author reddyraja
 */
object LifeCycleApp extends App{

  val actorSystem=ActorSystem("LifecycleActorSystem")
  val lifecycleActor=actorSystem.actorOf(Props[BasicLifecycleLoggingActor],"lifecycleActor")

   val deadLetterListener = actorSystem.actorOf(Props[MyCustomDeadLetterListener])
  actorSystem.eventStream.subscribe(deadLetterListener, classOf[DeadLetter])
  
  lifecycleActor!"hello"
   lifecycleActor!"hello"
  lifecycleActor!"stop"
  lifecycleActor!"hello" //Sending message to an Actor which is already stopped
  

  //wait for a couple of seconds before shutdown
  Thread.sleep(2000)
  actorSystem.shutdown()

}

class BasicLifecycleLoggingActor extends Actor with ActorLogging{

  log.info ("Inside BasicLifecycleLoggingActor Constructor")
  log.info (context.self.toString())
  override def preStart() ={
    log.info("Inside the preStart method of BasicLifecycleLoggingActor")
  }

  def receive = LoggingReceive{
    case "hello" => log.info ("hello")
  }

  override def postStop()={
    log.info ("Inside postStop method of BasicLifecycleLoggingActor")
  }

}

class MyCustomDeadLetterListener extends Actor {  
  def receive = {
    case deadLetter: DeadLetter => println(s"FROM CUSTOM LISTENER $deadLetter")
  }
}