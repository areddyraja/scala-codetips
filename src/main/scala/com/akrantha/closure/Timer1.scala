package com.akrantha.closure

object Timer1 {

  def oncePerSecond(): Unit =
    {
      while (true) {
        System.out.println("Time flies when you're having fun(ctionally)...")
        Thread.sleep(1000)
      }
    }
  def main(args: Array[String]): Unit =
    {
      oncePerSecond()
    }
}
  

