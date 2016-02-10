package iorelated

object ReadFromFile {
  import scala.io.Source
  def main(args: Array[String]) {
    println("Following is the content read:")
    Source.fromFile("src/main/scala/iorelated/ReadFromFile.scala").foreach {
      print
    }
  }
}