

package sorting/**
 * @author reddyraja
 * The elements are in acending order in the matrix. both columns and rows
 */
object SearchInMatrix {
  def findElement(array: Array[Array[Int]], elem: Int): Boolean = {
    
    var row = 0
    var col = array(0).length
    
    while(row < array.length && col > 0){
      if(array(row)(col) == elem){
        return true
      } else if(array(row)(col) > elem) {
        col -= 1 
      } else {
        row += 1
      }
    }
    return false
  }
}