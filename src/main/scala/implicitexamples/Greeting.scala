package implicitexamples

class PreferredPrompt(val preference: String)
class PreferredDrink(val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt,
                          drink: PreferredDrink) {

    println("Welcome, " + name + ". The system is ready.")
    print("But while you work, ")
    println("why not enjoy a cup of " + drink.preference + "?")
    println(prompt.preference)
  }
}

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master> ")
  implicit val drink = new PreferredDrink("tea")
}

object Greeting extends App {
  //commeting this line gives an error. 
  import JoesPrefs._
  Greeter.greet("Joe")
  Greeter.greet("Joe")(prompt, drink)
  Greeter.greet("Joe")

  //Impicitly this methos is getting called and the data is getting
  // converted to Int
  implicit def strToInt(x: String) = x.toInt

  println(math.max("123", 111))
}