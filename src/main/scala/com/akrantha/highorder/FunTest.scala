package com.akrantha.highorder

object FunTest extends scala.App {

  def apply(f: Int => String, v: Int) = f(v)
  val decorator = new Decorator("[", "]")
  println(apply(decorator.layout, 7))

  def fact(n: Int): Int = {
    if (n <= 0)
      1
    else
      n * fact(n - 1)
  }

}

class Decorator(left: String, right: String) {
  def layout[A](x: A) = left + x.toString() + right
}




