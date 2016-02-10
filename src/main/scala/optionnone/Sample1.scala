package optionnone

/**
 * @author reddyraja
 */
object Sample1 extends App {
  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  val y = toInt("foo")
  y match {
    case Some(num) => println(y.getOrElse(0))
    case None      => println(y.getOrElse(0))
  }

  println(y)
  val x = toInt("foo")
  println(x)

}