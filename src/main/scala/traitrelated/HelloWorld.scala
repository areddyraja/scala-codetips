package traitrelated
object HelloWorld extends App {
  println("Hello, World!")
  
}

trait Expr {
  def eval : Int
}

case class Number(n: Int) extends Expr {
  def eval = n
}

case class Plus(l: Expr, r: Expr) extends Expr {
  def eval = l.eval + r.eval
}

