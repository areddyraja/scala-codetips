package com.akrantha.closure

object Timer2 {
  def oncePerSecond(callback: () => Unit): Unit =
    {
      while (true) {
        callback()
        Thread.sleep(1000)
      }
    }

  def timeFlies(): Unit =
    { Console.println("Time flies when you're having fun(ctionally)..."); }

  def main(args: Array[String]): Unit =
    {
      oncePerSecond(timeFlies)
    }

}