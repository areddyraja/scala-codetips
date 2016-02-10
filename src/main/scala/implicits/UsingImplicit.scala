package implicits
import java.util.Calendar._
import java.util.Calendar

/**
 * @author reddyraja
 */
class UsingImplicit(val number: Int) {
  def days() = this
  def ago() = {
    val today = Calendar.getInstance()
    today.add(Calendar.DAY_OF_MONTH, -number)
    today.getTime()
  }

  implicit def int2Util(number: Int) {
    new UsingImplicit(number)
  }

  def main(args: Array[String]): Unit = {
    
  }
}

object UsingImplict extends App {
  println("Hello world")

  println(new UsingImplicit(2).days().ago)
  
}
 
