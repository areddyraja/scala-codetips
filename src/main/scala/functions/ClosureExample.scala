package functions

/**
 * @author reddyraja
 */

package otherscope {
  class Foo {
    // a method that takes a function and a string, and passes the string into
    // the function, and then executes the function
    def exec(f: (String) => Unit, name: String) {
      f(name)
    }
  }
}

/*
Not only did the sayHello method reference the vari‐
able hello from within the exec method of the Foo class on the first run (where hello
was no longer in scope), but on the second run, it also picked up the change to the hello
variable (from Hello to Hola). The simple answer is that Scala supports closure 
functionality, and this is how closures work.
 */

/**
 * You could continue to pass the sayHello method around so it gets
 * farther and farther away from the scope of the hello variable, but in
 * an effort to keep this example simple, it’s only passed to one method
 * in a class in a different package. You can verify that hello is not in
 * scope in the Foo class by attempting to print its value in that class or
 * in its exec method, such as with println(hello). You’ll find that the
 * code won’t compile because hello is not in scope there.
 */

object ClosureExample extends App {
  var hello = "Hello"
  def sayHello(name: String) { println(s"$hello, $name") }
  // execute sayHello from the exec method foo
  val foo = new otherscope.Foo
  foo.exec(sayHello, "Al")
  // change the local variable 'hello', then execute sayHello from
  // the exec method of foo, and see what happens
  hello = "Hola"
  foo.exec(sayHello, "Lorenzo")

  import scala.collection.mutable.ArrayBuffer
  val fruits = ArrayBuffer("apple")
  // the function addToBasket has a reference to fruits
  val addToBasket = (s: String) => {
    fruits += s
    println(fruits.mkString(", "))
  }

  buyStuff(addToBasket, "cherries")
  buyStuff(addToBasket, "grapes")

  def buyStuff(f: String => Unit, s: String) {
    f(s)
  }

  var more = 1
  val addMore = (x: Int) => { println (x + more) }
  
  addMore(10)

}