

package sorting/**
 * @author reddyraja
 *
 * https://www.youtube.com/watch?v=COk73cpQbFQ&list=PL2_aWCzGMAwKedT2KfDMB9YA5DgASZb3U&feature=iv&src_vid=3Bbm3Prd5Fo&annotation_id=annotation_4288718211
 */
object QuickSort extends App {

  var data: Array[Int] = Array(0, 1, 0, 0, 3, 2, 4, 1, 4, 3)
  println("abefore")

  data.foreach(println)

  quickSort(data, 0, data.length)

  println("after")
  data.foreach(println)

  def quickSort(data: Array[Int], start: Int, end: Int): Unit = {
    if (start < end) {
      var pIndex = partition(data, start, end)
      quickSort(data, 0, pIndex - 1)
      quickSort(data, pIndex + 1, end)
    }
  }

  def partition(data: Array[Int], start: Int, end: Int): Int = {
    var pIndex = start
    var pivot = data(end - 1)
    for (i <- start to end - 1 by 1) {
      if (data(i) <= pivot) {
        val temp = data(pIndex)
        data(pIndex) = data(i)
        data(i) = temp
        pIndex += 1
      }
    }

    if (pIndex <= end-1) {
      val temp = data(pIndex)
      data(pIndex) = data(end - 1)
      data(end - 1) = temp
    }
    return pIndex
  }
}