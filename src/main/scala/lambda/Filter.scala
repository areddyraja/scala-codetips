package lambda

/**
 * @author reddyraja
 */
object Filter extends App {
  val filter = (predicate: Int => Boolean, xs: List[Int]) => {
    for (x <- xs; if predicate(x)) yield x
  }
  val even = (x: Int) => x % 2 == 0
  val odd = (x: Int) => x % 2 == 1

  val candidates = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val evenValues = filter(even, candidates)
  evenValues map (e => println(s"even ${e}"))
  val oddValues = filter(odd, candidates)
  oddValues map (e => println(s"odd ${e}"))

}