package com.akrantha.generics

object LowerBoundTestExample extends scala.App {

  val empty: ListNode[Null] = ListNode(null, null)
  val strList: ListNode[String] = empty.prepend("hello")
    .prepend("world")
  val anyList: ListNode[Any] = strList.prepend(12345)

}

case class ListNode[+T](h: T, t: ListNode[T]) {
  def head: T = h
  def tail: ListNode[T] = t
  //def prepend(elem: T): ListNode[T] =
  //ListNode(elem, this)
  def prepend[U >: T](elem: U): ListNode[U] =
    ListNode(elem, this)
}
