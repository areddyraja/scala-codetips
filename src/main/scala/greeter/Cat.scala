package greeter

trait Friend {
  val name: String
  def listen() { println("Your friend " + name + "is listening") }
}

class Animal


class Cat(val name: String) extends Animal {
}

class Human(val name: String) extends Friend

class Man(override val name: String) extends Human(name)

class Woman(override val name: String) extends Human(name)

class Dog(val name: String) extends Animal with Friend {
  override def listen = println(name + "'s listening quietly")
}

trait TraversableOnce[T] {

}


class Tenova[T,P,V] {}

object MainProgram {
  def main(args: Array[String]): Unit = {
    println("Hell Animal world")

    val dog: Dog = new Dog("German Shepherd")
    dog.listen

    val john = new Man("Sara")
    john.listen

    val john2: Friend = john
    john2.listen

    val newDog: Friend = dog
    newDog.listen

    println(max(2, 5, 3, 7, 1, 6))

  }
  def max(values: Int*) = values.foldLeft(values(0)) { Math.max }

}