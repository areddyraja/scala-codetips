package functions

/**
 * @author reddyraja
 */

object MoreFunctions extends App {

  def executeFunction(callback: () => Unit) {
    callback()
  }

  def executeFunction2(f: () => Unit) {
    f()
  }

  def plusOne(i: Int) = i + 1

  val sayHello = () => { println("Hello") }
  val sayBye = () => { println("bye") }

  def sum(f: Int => Int, a: Int, b: Int): Int = if (a > b) 0 else f(a) + sum(f, a + 1, b)

  // these three functions use the sum() function
  def sumInts(a: Int, b: Int): Int = sum(id, a, b)
  def sumSquares(a: Int, b: Int): Int = sum(square, a, b)
  def sumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)

  // three functions that are passed into the sum() function
  def id(x: Int): Int = x
  def square(x: Int): Int = x * x
  def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)

  // this simply prints the number 10
  println("sum ints 1 to 4 = " + sumInts(1, 4))

  println("sum ints 1 to 4 = " + sumSquares(1, 4))
  println("sum ints 1 to 4 = " + sumPowersOfTwo(1, 3))

  executeFunction(sayHello)
  executeFunction(sayBye)

  def executeXTimes(callback: () => Unit, numTimes: Int) {
    for (i <- 1 to numTimes) callback()
  }

  executeXTimes(sayHello, 3)

  def executeAndPrint(f: (Int, Int) => Int, x: Int, y: Int) {
    val result = f(x, y)
    println(result)
  }

  val sum = (x: Int, y: Int) => x + y
  val multiply = (x: Int, y: Int) => x * y

  executeAndPrint(sum, 2, 9)
  executeAndPrint(multiply, 3, 9)

  //functions that return functions
  (s: String) => { "prefix" + " " + s }

  def saySomething(prefix: String) = (s: String) => {
    println(prefix + " " + s)
  }

  val sayHello2 = saySomething("Hello")
  sayHello2("Al")

  def returnfFunction() = () => {
    println("Hello world: This is 99")
  }

  val f99 = returnfFunction()
  f99()

  //some more on functions returning functions
  def greeting(language: String) = (name: String) => {
    val english = () => "Hello, " + name
    val spanish = () => "Buenos dias, " + name
    language match {
      case "english" =>
        println("returning 'english' function")
        english()
      case "spanish" =>
        println("returning 'spanish' function")
        spanish()
    }
  }

  val hello = greeting("english")
  val buenosDias = greeting("spanish")
  hello("Al")
  buenosDias("Lorenzo")

  //declaring variables as functions, different ways of declaring

  // function different vasks

  def modMethod(i: Int) = i % 2 == 0
  def modMethod1(i: Int) = { i % 2 == 0 }
  def modMethod2(i: Int): Boolean = i % 2 == 0
  def modMethod3(i: Int): Boolean = { i % 2 == 0 }

  def modMethod4: Int => Boolean = i => { i % 2 == 0 }

  //as variable
  val modFunction = (i: Int) => i % 2 == 0

  val f: (Int) => Boolean = i => { i % 2 == 0 }
  val f2: Int => Boolean = i => { i % 2 == 0 }
  val f3: Int => Boolean = i => i % 2 == 0
  val f4: Int => Boolean = _ % 2 == 0

  val add = (x: Int, y: Int) => { x + y }
  val add2 = (x: Int, y: Int) => x + y

  val add3: (Int, Int) => Int = (x, y) => { x + y }
  val add4: (Int, Int) => Int = (x, y) => x + y

  val addThenDouble: (Int, Int) => Int = (x, y) => {
    val a = x + y
    2 * a
  }

  val c = scala.math.cos _
  println(c(0))

}