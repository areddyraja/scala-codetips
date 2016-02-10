

package sorting/**
 * @author reddyraja
 */
object Timer  {
  
  def oncePerSecond(callback:() => Unit): Unit = {
    var  check = true  
    while(check) {
        callback(); Thread.sleep(1000)
        check = false
      }
  }
  
  
  def timeFlies(){
    println("time flies like an arrow")
  }
  
  def main(args: Array[String]){
    oncePerSecond { () => println("new version of time flies") }
    oncePerSecond { timeFlies }
    
  }
  
}