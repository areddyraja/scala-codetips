package com.akrantha.closure

import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach

class something

object SimpleClouseDemo1 {

  def main(args: Array[String]): Unit = {
    args.filter((arg: String) => arg.startsWith("G"))
      .foreach((arg: String) => Console.println("Found " + arg))

    val multiplier = (i: Int) => i * 10

    println("Vlaue of multiplier is " + multiplier(1))

    val factor: Int = 5;
    val multiplier2 = (i: Int) => i * factor
    println("Vlaue of multiplier is " + multiplier(10))
    
    val t = Tuple3(1,"Hello", Console)
    println("Concatednates String: " + t.toString())
    
    println("Item1 " + t._1)

    println("Item1 " + t._2)
    println("Item1 " + t._3)
    
    t.productIterator.foreach{i=>println("Value=" + i)}
    
    
  }

}

