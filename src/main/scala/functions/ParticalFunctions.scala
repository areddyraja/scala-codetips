package functions

/**
 * @author reddyraja
 */
object ParticalFunctions extends App {
  val divide = new PartialFunction[Int, Int] {
    def apply(x: Int) = 42 / x
    def isDefinedAt(x: Int) = x != 0
  }

  divide.isDefinedAt(1)
  if (divide.isDefinedAt(1)) divide(1)
  divide.isDefinedAt(0)

  val convertLowNumToString = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three", "four", "five")
    def apply(i: Int) = nums(i - 1)
    def isDefinedAt(i: Int) = i > 0 && i < 6
  }

  val convert1to5 = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three", "four", "five")
    def apply(i: Int) = nums(i - 1)
    def isDefinedAt(i: Int) = i > 0 && i < 6
  }

  val convert6to10 = new PartialFunction[Int, String] {
    val nums = Array("six", "seven", "eight", "nine", "ten")
    def apply(i: Int) = nums(i - 6)
    def isDefinedAt(i: Int) = i > 5 && i < 11
  }

  val handle1to10 = convert1to5 orElse convert6to10

  val divide3: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }

  //val x1 = List(0,1,2) map { divide3 }
  //x1 foreach println

  val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::   ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

  def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.filter(wf => wf._2 > 3 && wf._2 < 25).map(_._1)
  wordsWithoutOutliers(wordFrequencies).foreach(println)

  def wordsWithoutOutliers2(wordFrequencies: Seq[(String, Int)]): Seq[String] =
    wordFrequencies.filter { case (_, f) => f > 3 && f < 25 } map { case (w, _) => w }

  wordsWithoutOutliers2(wordFrequencies).foreach(println)

}