package katas

/**
 * @author reddyraja
 */
object FizzBuzz {
  def getResult(number: Int) = {
    var result = ""
    if (number % 3 == 0) result = "fizz"
    if (number % 5 == 0) result += "buzz"
    if(result == ""){
      number.toString
    } else
      result
  }
}