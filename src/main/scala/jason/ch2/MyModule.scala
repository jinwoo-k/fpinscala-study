package jason.ch2

/**
  * Created by jason.kim on 2017. 1. 16..
  */
object MyModule extends App {
  def abs(n: Int): Int = Math.abs(n)

  def factorial(n: Int): Int = 1.to(n).product

  def fib(n: Int): Int = n match {
    case 1 => 1
    case 2 => 1
    case _ => {
      fib(n - 2) + fib(n - 1)
    }
  }

  def format(name: String, f: Int => Int)(n: Int): String = {
    s"The $name value of $n is ${f(n)}"
  }

  def findFirst[A](ss: Array[A])(p: A => Boolean): Option[Int] = {
    ss.zipWithIndex.find(t => p(t._1)).map(_._2)
  }

  def isSorted[A](as: Array[A])(ordered: (A, A) => Boolean): Boolean = {
    as.sliding(2).forall(l => ordered(l.head, l.last))
  }

  val lessThan = new Function2[Int, Int, Boolean] {
    override def apply(a: Int, b: Int): Boolean = a < b
  }

  val lessThan2 = (a: Int, b: Int) => a < b

  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = f(a, _)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a: A, b: B) => f(a)(b)
  def uncurry2[A, B, C](f: A => B => C): (A, B) => C = f(_)(_)

  def compose[A, B, C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))

  println(format("ABS", abs)(-1000))
  println(format("Factorial", factorial)(4))
  println(format("Fibonacci", fib)(10))
  println(findFirst(Array("s1", "s2", "s3", "s4", "s5"))(_ == "s1"))
  println(findFirst(Array("s1", "s2", "s3", "s4", "s5"))(_ == "s4"))
  println(isSorted(Array(1,2,4,6,5))(_ <= _))

  println(partial1(1, (a: Int, b: Int) => a + b)(3))
  println(compose((a: Int) => a * 2, (b: Int) => b + 3)(10))



}
