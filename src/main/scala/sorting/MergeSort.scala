

package sorting/**
 * @author reddyraja
 */
object MergeSort extends App {

  var data: Array[Int] = Array(0, 1, 0, 0, 3, 2, 4, 1, 4, 3)
  data.foreach(println)

  MergeSort(data)
  println("Final output")

  data.foreach(println)

  def MergeSort(data: Array[Int]) {
    var dataHelper: Array[Int] = new Array[Int](data.length)
    mergeSort(data, dataHelper, 0, data.length - 1);

  }

  def mergeSort(data: Array[Int], dataHelper: Array[Int], low: Int, high: Int): Unit = {
    if (low < high) {
      val middle = (low + high) / 2
      mergeSort(data, dataHelper, low, middle)
      mergeSort(data, dataHelper, middle + 1, high)
      merge(data, dataHelper, low, middle, high)
    }
  }

  def merge(data: Array[Int], dataHelper: Array[Int], low: Int, middle: Int, high: Int) = {
    for (i <- 0 to data.length - 1 by 1) {
      dataHelper(i) = data(i)
    }
    println("Data helper data")
    dataHelper.foreach(println)

    var helperLeft = low
    var helperRight = middle
    var current = low

    while (helperLeft <= middle && helperRight <= high) {
      if (dataHelper(helperLeft) <= dataHelper(helperRight)) {
        data(current) = dataHelper(helperLeft)
        helperLeft += 1
      } else {
        data(current) = dataHelper(helperRight)
        helperRight += 1
      }
      current += 1
    }

    while (helperLeft < middle) {
      data(current) = dataHelper(helperLeft)
      current += 1
      helperLeft += 1
    }

    while (helperRight < high) {
      data(current) = dataHelper(helperRight)
      current += 1
      helperRight += 1
    }

  }
}