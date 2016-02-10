

package sorting/**
 * @author reddyraja
 */
object BitWiseOperations {
  def getBit(num: Int, position: Int): Boolean = {
    return (num & (1 << position)) != 0
  }

  def setBit(num: Int, position: Int): Int = {
    return num | (1 << position)
  }

  def clearBit(num: Int, position: Int): Int = {
    return num & ~(1 << position)
  }

  def clearBitsMSBThroughI(num: Int, position: Int): Int = {
    val mask = (1 << position) - 1
    return num & mask
  }

  def clearBitsIThroughZero(num: Int, position: Int): Int = {
    val mask = ~(1 << position + 1) - 1
    return num & mask
  }

  def updateBit(num: Int, position: Int, v: Int): Int = {
    val mask = ~(1 << position)
    return (num & mask) | v << position
  }

  /*
   * You aregiven two 32-bit numbers, Nand M, andtwo bit positions, land j.Write
    
a method to insert M into Nsuch that M starts at bit j and ends at bit i. 
You can assume that the bits j through i have enough space to fit all of M. 
That is, if M = 10011, you canassumethat there areat least 5 bits between j and i. 
You would not,forexample, have j = 3 and i = 2,because M could notfully fit 
between bit 3 and bit 2.

Input: N=10000000000,M=10011,i=2,j=6 Output:N = 10001001100

   
   */

  def updateBits(n: Int, m: Int, i: Int, j: Int): Int = {
    var allones = ~0

    val right = allones & (1 << i + 1)
    val left = allones & ((1 << i) - 1)

    val cleared = right | left

    //clear 
    val shifted_m = m << i
    return (n & cleared) | shifted_m

  }

  /* Write a function to determine the number of bits required to convert integer A to
    integer B.
    EXAMPLE Input: 31,14 Output: 2
  */
  
  def bitsRequiredFromAtoB(a:Int, b:Int) : Int = {
    var c:Int = a ^ b
    var count = 0
    while (c!= 0){
      c = c << 1
      count += c & 1
    }
    return count
  }

  /*
   * Write a program to swap odd and even bits in an 
   * integer with as few instructions as possible 
   * (e.g., bit 0 and bit! are swapped, bit 2 and bit 3 are swapped, and soon).
   */
  
  def swapOddAndEvenBits(num: Int) : Int = {
    val swapeven =  (num & 0xaaaaaaaa) >> 1
    val swapodd = (num & 0x55555555) << 1
    return swapeven | swapodd
  }
  
  
}