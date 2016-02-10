package functions

/**
 * @author reddyraja
 */
object partial extends App {
  val sum = (a: Int, b: Int, c: Int) => {  println (a + b + c )}

  val f = sum(1, 2, _: Int)
  f(3)
  
  f(10)
}