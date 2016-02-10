package com.akrantha.closure

object Timer4 {

  def periodicCall(seconds: Int, callback: () => Unit): Unit =
    {
      while (true) {
        callback()
        Thread.sleep(seconds * 1000)
      }
    }
  def main(args: Array[String]): Unit =
    {
      periodicCall(1, () =>
        Console.println("Time flies... oh, you get the idea."))
    }

  def somefunction(value: Int, byte: BigDecimal): Unit = {

  }
}
