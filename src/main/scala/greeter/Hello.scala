package greeter

class Animal2[T](seed: Int)

private class SampledRDD[T: Animal2](seed: Int) {

}
case class Money(amount: Int = 1, currency: String = "USD") {

  def +(other: Money): Money = Money(amount + other.amount)

  def inject(arr: Array[Int], initial: Int, operation: (Int, Int) => Int): Int = {
    var carryOver = initial
    arr.foreach(element => carryOver = operation(carryOver, element))
    carryOver
  }

  def inject2(arr: Array[Int], initial: Int)(operation: (Int, Int) => Int): Int = {
    var carryOver = initial
    arr.foreach(element => carryOver = operation(carryOver, element))
    carryOver
  }

  def foo(a: Int)(b: Int)(c: Int) {}

  def bar()()() {

  }

  def loopThrough(number: Int)(closure: Int => Unit) {
    for (i <- 1 to number) { closure(i) }
  }

  def something() {
    var result = 0
    val addIt = { value: Int => result += value }
    loopThrough(10) { addIt }
    println("sum of values from 1 to 10 is " + result)

  }

  def somethingelse() {
    var product = 1
    loopThrough(5) { product *= _ }
    println("Product of values from 1 to 5 is " + product)
  }

}

class Equipment(val routine: Int => Int) {
  def simulate(input: Int) = {
    print("Running simulation...")
    routine(input)
  }
}

object Hello extends App {
  println("Hello, World!")
  val notMuch = new Money(2, "INR")
  println(notMuch.amount)
  println((notMuch + new Money(2, "INR")).amount)

  val money = Money(12) + Money(13)
  println(money.amount)

  val arr = Array(2, 3, 5, 1, 6, 4)
  println(money.inject(arr, 0, (carryover, element) => carryover + element))

  println(money.inject2(Array(2, 3, 5, 1, 6, 4), 0) { (carryover, element) => carryover + element })

  val equipment1 = new Equipment({ input => println("value is " + input); input })
  val equipment2 = new Equipment({ input => println("value is " + input); input })
  equipment1.simulate(3)
  equipment2.simulate(4)

  val calculator = { input: Int => println("value is " + input); input }

  val equipment3 = new Equipment(calculator)
  val equipment4 = new Equipment(calculator)

  equipment3.simulate(5)
  equipment4.simulate(4)

  println(money.inject2(Array(2, 3, 5, 1, 61, 4), Integer.MIN_VALUE) { (carryover, element) => Math.max(carryover, element) })

  money.something
  money somethingelse

  //using sets
  val feeds1 = Set("blog.toolshed.com", "pragdave.pragprog.com",
    "pragmactic-osxer.blogspot.com", "vita-contemplativa.blogspot.com")
  val feeds2 = Set("blog.toolshed.com", "martinfowler.com/bliki")

  val blogSpotFeeds = feeds1.filter(_ contains "blogspot")
  println("blogspot feeds: " + blogSpotFeeds.mkString(", "))

  val feeds = feeds1 ++ feeds2
  println("blogspot feeds: " + blogSpotFeeds.mkString(", "))

  val commonFeeds = feeds1 ++ feeds2
  println("common feeds: " + commonFeeds.mkString(", "))

  val urls = feeds1 map ("http://" + _)
  //println("One url: " + urls.toArray((0))
  println("urls appended feeds: " + urls.mkString(", "))

  //Using Maps

  val feeds3 = Map("Andy Hunt" -> "blog.toolshed.com",
    "Dave Thomas" -> "pragdave.pragprog.com",
    "Dan Steinberg" -> "dimsumthinking.com/blog")

  val filterNameStartWithD = feeds3 filterKeys (_ startsWith "D")
  println("# of Filtered: " + filterNameStartWithD.size)

  val filterNameStartWithDAndBlogInFeed = feeds3 filter { element =>
    val (key, value) = element
    (key startsWith "D") && (value contains "blog")
  }

  println("# of feeds with auth name D* and blog in URL: " +
    filterNameStartWithDAndBlogInFeed.size)

  println("Get Andy's Feed: " + feeds3.get("Andy Hunt"))
  println("Get Bill's Feed: " + feeds3.get("Bill Who"))

}

class ALS private {
  
}








