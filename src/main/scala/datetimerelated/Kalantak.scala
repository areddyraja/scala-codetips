package datetimerelated

import org.joda.time.DateTime

import java.util.TimeZone
import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime
import java.util.Calendar

object Kalantak extends App {

  val date = new DateTime getMillis

  println(date)
  println(currentTimeIn("Asia/Kolkata").getMillis)

  val date2 = new DateTime().toDateTime()
  println(date2)

  val date3 = new DateTime("2015-05-02").equals(new DateTime("2015-05-02"))
  println(date3)

  val greater = new DateTime("2015-05-02").equals(new DateTime("2015-05-02"))

  val t1 = new DateTime("2015-05-02")
  val t2 = new DateTime("2015-05-03")

  t1.isAfter(t2)
  println("t1 is before t2" + t1.isAfter(t2))

  println(t1.getZone)

  def currentTimeIn(timeZone: String) = LocalDateTime.now toDateTime (DateTimeZone forID timeZone)

}