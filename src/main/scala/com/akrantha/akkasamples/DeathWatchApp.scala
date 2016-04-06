package com.akrantha.akkasamples
import akka.actor.{ PoisonPill, Actor, ActorLogging, actorRef2Scala, ActorSystem, Kill }
import scala.util.Random
import akka.actor.{ Terminated, Props }
import QuoteRepositoryProtocol._
import TeacherProtocol._

/**
 * @author reddyraja
 */
object DeathWatchApp extends App {
  val actorSystem = ActorSystem("DeathWatchApp")

  //  val actor = actorSystem.actorOf(Props[QuoteRepositoryActor])
  val teacher = actorSystem.actorOf(Props[TeacherActorWatcher])

  //  actor ! (new QuoteRepositoryRequest)
  //  actor ! new QuoteRepositoryRequest
  //  actor ! new QuoteRepositoryRequest
  teacher !  QuoteRequest
  teacher !  QuoteRequest
  teacher !  QuoteRequest
  teacher !  QuoteRequest

}

class QuoteRepositoryActor() extends Actor with ActorLogging {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  var repoRequestCount: Int = 1

  def receive = {

    case QuoteRepositoryRequest => {

      log.info("QuoteRequest received")
      if (repoRequestCount > 3) {
        self ! PoisonPill
      } else {
        //Get a random Quote from the list and construct a response
        val quoteResponse = QuoteRepositoryResponse(quotes(Random.nextInt(quotes.size)))

        log.info(s"QuoteRequest received in QuoteRepositoryActor. Sending response to Teacher Actor $quoteResponse")
        repoRequestCount = repoRequestCount + 1
        sender ! quoteResponse
      }

    }

  }

}

class TeacherActorWatcher extends Actor with ActorLogging {

  val quoteRepositoryActor = context.actorOf(Props[QuoteRepositoryActor], "quoteRepositoryActor")
  context.watch(quoteRepositoryActor)

  def receive = {
    case QuoteRequest => {
      quoteRepositoryActor ! QuoteRepositoryRequest
    }
    case Terminated(terminatedActorRef) => {
      log.error(s"Child Actor {$terminatedActorRef} Terminated")
    }
  }
}

object QuoteRepositoryProtocol {

  case class QuoteRepositoryRequest()
  case class QuoteRepositoryResponse(quoteString: String)

}
object TeacherProtocol {

  /*
   * The Student sends this message to request for a Quotation 
   * 
   */
  case class QuoteRequest()

  /* 
   * The TeacherActor responds back to the Student with this message object
   * The actual quote string is wrapped inside the response.
   * 
   */
  case class QuoteResponse(quoteString: String)

}
