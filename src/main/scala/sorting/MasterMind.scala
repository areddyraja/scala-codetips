package sorting/**
 * @author reddyraja
 */
object MasterMind extends App {

  val MAX_COLORS = 4;

  var frequencies: Array[Int] = new Array[Int](MAX_COLORS)
  val x:Result = estimate("GBRB","GBYR")
  x.printResult

  def code(c: Char): Int = {
    c match {
      case 'B' => return 0
      case 'G' => return 1
      case 'R' => return 2
      case 'Y' => return 3
      case _   => return -1
    }
  }

  def estimate(solution: String, guess: String): Result = {
    if (guess.length() != solution.length()) return null

    var res = new Result()
    for (i <- 0 to solution.length()-1) {
      if (guess(i) == solution(i)) {
        res.hits += 1
      } else {
        val a: Int = code(solution.charAt(i))
        frequencies(a) += 1
      }
    }

    for (i <- 0 to guess.length()-1) {
      val a = code(guess.charAt(i))
      if (a >= 0 && frequencies(a) > 0) {
        if (guess(i) != solution(i)) {
          res.phits += 1
        }
      }
    }

    return res
  }

}

class Result {
  var hits = 0
  var phits = 0
  def printResult = {
    println("hits=" + hits + "phits" + phits)
  }
}




