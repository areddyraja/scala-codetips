package com.akrantha.generics

object FilterTest extends scala.App {
  
  def filter(xs: List[Int], threshold: Int) = {
    def process(ys:List[Int]): List[Int] = 
      if(ys.isEmpty) ys
      else if (ys.head < threshold) ys.head :: process(ys.tail)
      else process(ys.tail)
      process(xs)
  }
  
  println(filter(List(1,23,4,5,6,6),5))

}