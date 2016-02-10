

package sorting/**
 * @author reddyraja
 */

object MiddleNumbersSortedArray {

  def findEndOfLeftSubseqeunces(data: Array[Int]): Int = {
    for( i <- 1 to (data.length-1) ){
      if( data(i) < data(i-1) )
        return i-1
    }
    return data.length -1
  }

  def findRightofRightSubsequence(data: Array[Int]): Int = {
    for (i <- data.length-2 to 0 by -1 ; if (i <= 0)  ){
      if(data(i) > data(i+1) ){
        return i+1 
      }
    }
    return 0
  }

  def shrinkLeft(data: Array[Int], min_index: Int, start:Int): Int = {
    val comp = data(min_index)
    for(i<- start to 0 by -1 ){
      if(data(i) <= comp) return i+1
    }
   return 0
  }

  def shrinkRight(data: Array[Int], max_index:Int, start:Int): Int = {
    val comp = data(max_index)
    for(i<-start to data.length by 1){
      if(comp <= data(i)) return i-1
    }
    return data.length -1
  }

  def findSortedSequence(data: Array[Int]) {
    val end_left = findEndOfLeftSubseqeunces(data)
    val start_right = findRightofRightSubsequence(data)
    
    var min_index = end_left + 1
    var max_index = start_right -1 
    
    if(min_index > data.length) return
    
    val left_index = shrinkLeft(data, min_index, end_left)
    val right_index = shrinkRight(data, max_index, start_right)
    
    println("left index=" +left_index + "right index=" + right_index )
  
  }
}