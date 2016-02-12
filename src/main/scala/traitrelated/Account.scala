package traitrelated

/**
 * @author reddyraja
 */

trait IOer {
  def write(bytes: Array[Byte])
  def read(n: Int): Array[Byte]
}

trait Reader {
  def read(n: Int): Array[Byte]
}

trait Writer {
  def write(bytes: Array[Byte])
}

abstract class something {
  val ioer: IOer with Reader with Writer
}

class Account

trait Logged {
  def log(msg: String) {}
}

trait Logged2 {
  def log(msg: String) //abstract method
}

class SavingsAccount extends Account with Logged {
  def withDraw(amount: Int) {
    if (amount > 15)
      log("Cannot withdaw more than 15")
  }
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) { println(msg) }

  trait TimestampLogger2 extends Logged2 {

    abstract override def log(msg: String) {
      super.log(new java.util.Date() + "" + msg)
    }
  }
}

trait TimestampLogger extends Logged {
  override def log(msg: String) { super.log(new java.util.Date() + " " + msg) }
}

trait ShortLogger extends Logged {
  val maxLength = 15
  override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
  }
}

object AllAccounts extends App {
  val x = new SavingsAccount
  x.withDraw(20)

  val y = new SavingsAccount with ConsoleLogger
  y.withDraw(20)

  val p = new SavingsAccount with ConsoleLogger with TimestampLogger with ShortLogger
  p.withDraw(20)

  val q = new SavingsAccount with ConsoleLogger with ShortLogger with TimestampLogger
  q.withDraw(20)

}

 