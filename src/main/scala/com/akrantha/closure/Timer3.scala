package com.akrantha.closure

object Timer3 {

  def oncePerSecond(callback: () => Unit): Unit =
    {
      while (true) {
        callback()
        Thread.sleep(1000)
      }
    }

  def main(args: Array[String]): Unit =
    {
      oncePerSecond(() =>
        Console.println("Time flies... oh, you get the idea."))
    }
}