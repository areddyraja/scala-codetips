package currytest

object HelloWorld2 {
def main(args: Array[String]) {
      println("Hello, world!") // prints Hello World
   }

//regular function
def addOne(m: Int): Int = m+1

//anonymous
val  addOneA = (x:Int) => x+1


//variable length args
def capitalizeAll(args: String*) = {
  args.map { arg =>
    arg.capitalize
  }
}

def multiply(m: Int)(n: Int): Int = m*n

//multipl arguments with curried functions
multiply(2)(3)

//curried functions
val times2 = multiply(2) _

//curried
times2(3)

//adder method general
def adder(m:Int, n:Int): Int = m + n

//curried one .. method
val curry_adder = (adder _ ).curried

val addTwo = curry_adder(2)
addTwo(4)

val c = new Calc

//method
c.minc

//function returns the function as value .. 
//use parenthesis to return the execution of the function
c.finc

}


class  Calc {
  var acc = 0;
  def minc = { acc = acc + 1}
  def finc = { () => acc = acc + 1}
  def display = { println(acc)}
}
