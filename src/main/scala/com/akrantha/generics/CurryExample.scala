package com.akrantha.generics

object CurryExample extends scala.App {

  def foo(a: Int)(b: Int)(c: Int) {  println("a=" + a + " b= " + b + " c " + c)}

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

  val array = Array(1, 2, 3, 4, 5)

  //find sum
  val sum = inject(array, 0, (carryOver, elem) => carryOver + elem)
  println("Sum of elements in array " + array.toString() + " is " + sum)

  //maximum value
  val max = inject(array, Integer.MIN_VALUE, (carryOver, elem) => Math.max(carryOver, elem))
  println("Max of elements in array " + array.toString() + " is " + max)

  val sum2 = inject2(array, 0) { (carryOver, elem) => carryOver + elem }
  println("Sum of elements in array " + array.toString() + " is " + sum2)
  
  foo{1}{2}{3}
}


