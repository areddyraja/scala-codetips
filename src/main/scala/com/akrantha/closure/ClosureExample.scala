package com.akrantha.closure

object ClosureExample extends scala.App {

  val equipment1 = new Equipment({ input => println("value is " + input); input })
  val equipment2 = new Equipment({ input => println("value is " + input); input })
  equipment1.simulate(3)
  equipment2.simulate(4)

  val calculator = { input: Int => println("value is " + input); input }

  val equipment3 = new Equipment(calculator)
  val equipment4 = new Equipment(calculator)

  equipment3.simulate(5)
  equipment4.simulate(4)

  
  something
  somethingelse
  
  // this is loo thro
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

 

