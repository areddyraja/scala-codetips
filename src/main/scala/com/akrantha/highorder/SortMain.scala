package com.akrantha.highorder

object SortMain {

  def main(args: Array[String]): Unit = {
    val numbers = Array(7, 10, 100, 34, 35, 1, 2, 3, 4)
    new Sorting().sort(numbers)
    numbers foreach println

    val numbers2 = Array(7, 10, 100, 34, 35, 1, 2, 3, 4)
    val numbers3 = new newsort().sort(numbers2)
    numbers3 foreach println
  }

}

class Sorting {
  def sort(xs: Array[Int]) {
    def swap(i: Int, j: Int) {
      val t = xs(i);
      xs(i) = xs(j);
      xs(j) = t
    }

    def sort1(l: Int, r: Int) {
      val pivot = xs((l + r) / 2)
      var i = l
      var j = r
      while (i <= j) {
        while (xs(i) < pivot) i += 1
        while (xs(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j) sort1(l, j)
      if (j < r) sort1(i, r)
    }
    sort1(0, xs.length - 1)
  }

}

//has two copies of the array; does not return the same.. but becomes more readable

class newsort {
  def sort(xs: Array[Int]): Array[Int] = {
    if (xs.length <= 1) xs
    else {
      val pivot = xs(xs.length / 2)
      Array.concat(
        sort(xs filter (pivot >)), xs filter (pivot ==), sort(xs filter (pivot <)))
    }
  }
}


