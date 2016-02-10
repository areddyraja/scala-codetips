package trysuccess

import scala.io.Source
import scala.util.{ Try, Success, Failure }
/**
 * @author reddyraja
 */
object SimpleTryScala extends App {
  val filename = "/etc/passwd"

  def readTextFile(filename: String): Try[List[String]] = {
    Try(Source.fromFile(filename).getLines.toList)
  }

  readTextFile(filename) match {
    case Success(lines) => lines.foreach(println)
    case Failure(f)     => println(f)
  }
}