package com.akrantha.highorder

class HighOrderFunction {

}

object MainProgram {

  def main(args: Array[String]): Unit = {
    def apply(f: Int => String, v: Int) = f(v)

    def layout[T](x: T) = "[" + x.toString() + "]"

    println(apply(layout, 10))

    //callby name
    callByNameExample

  }

  def callByNameExample() {
    //Typically parameters to functions are by values.
    //Value is know before it is passed to the function
    //If we need to write a function 
    delayed(time())
  }

  def time() = {
    println("Getting time in nano sec")
    System.nanoTime()
  }

  def delayed(t: => Long) = {
    println("IN delayed method")
    println("Param: " + t)
    t
  }

}