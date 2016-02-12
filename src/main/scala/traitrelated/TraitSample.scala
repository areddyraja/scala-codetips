package traitrelated

object Main {
  def main(args: Array[String]) {
    val birds = List(
      new Pigeon,
      new Hawk,
      new Frigatebird)

    birds.foreach(bird => bird.fly())

    //    val swimmingBirds = List(
    //    new Pigeon,
    //    new Hawk,
    //    new Penguin)
    //
    //    swimmingBirds.foreach(bird => bird.swim())
  }

}

abstract class Bird {
}

trait Flying {
  def flyMessage: String
  def fly() = println(flyMessage)
}

trait Swimming {
  def swim() = println("I'm swimming")
}

//abstract class Bird {
//  def flyMessage: String
//  def fly() = println(flyMessage)
////  def swim() = println("I'm swimming")
//}

//class Pigeon extends Bird {
//  val flyMessage = "I'm a good flyer"
//}

//class Hawk extends Bird {
//  val flyMessage = "I'm an excellent flyer"
//}

class Pigeon extends Bird with Swimming with Flying {
  val flyMessage = "I'm a good flyer"
}

class Hawk extends Bird with Swimming with Flying {
  val flyMessage = "I'm an excellent flyer"
}

class Frigatebird extends Bird   with Flying {
  val flyMessage = "I'm an excellent flyer"
}

class Penguin extends Bird with Swimming with Flying {
    val flyMessage = "I'm an excellent flyer"
}



