package main.scala

/**
  * Created by lu.j on 17. 1. 17..
  */
object chapter2 extends App{
  // 2.1 fibonaci
  def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int, acc1: Int): Int =
      if (n <= 1) acc
      else go(n - 1, acc1, acc + acc1)

    go(n, 0, 1)
  }
  println(fib(11))

  // 2.2 isSorted 함수
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean = {
      if (n >= as.length - 1) true
      else if (!ordered(as(n), as(n + 1))) false
      else loop(n + 1)
    }

    loop(0)
  }

  val arr = Array(1, 4, 3, 2)
  val arr2 = Array(1, 2, 3, 4)

  def ordering(a: Int, b: Int): Boolean = {
    if (a > b) false
    else true
  }

  println(isSorted(arr, ordering))
  println(isSorted(arr2, ordering))


  // 2.3 curry
  // exam
  def partial[A, B, C](a: A, f: (A,B) => C): B => C = {
    (b: B) => f(a, b)
  }
  // practice
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    (a: A) => (b: B) => f(a, b)
  }

  // 2.4 uncurry
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a: A, b: B) => f(a)(b)
  }

  // 2.5 composition
  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }
}
