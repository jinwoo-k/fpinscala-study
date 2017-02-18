package jason.ch5

import jason.ch5.Stream._

/**
  * Created by jason.kim on 2017. 2. 12..
  */
object Exercise5_4 extends App {

  lazy val ones: Stream[Int] = Stream.cons(1, ones)
  println(s"Take 5 elements from ones : ${ones.take(5).toList}")
  println(s"Exists _ % 2 in ones : ${ones.exists(_ % 2 != 0)}")

  // example 5.8
  val twos = Stream.constant(2)
  println(s"Take 5 elements from twos : ${twos.take(10000).toList}")

  // example 5.9
  val fromThree = Stream.from(3)
  println(s"Take 5 elements from (fromThree) : ${fromThree.take(5).toList}")

  // example 5.10
  val fib = Stream.fibs()
  println(s"Take 10 elements from fib : ${fib.take(10).toList}")


  // example 5.11 ~ 5.12
  val fromThreeViaUnfold = Stream.unfold(3)(s => Some(s, s + 1))
  println(s"Take 5 elements from (fromThreeViaUnfold) : ${fromThreeViaUnfold.take(5).toList}")
  val fibViaUnfold = Stream.unfold((0, 1))(t => Some(t._1 + t._2, (t._2, t._1 + t._2)))
  println(s"Take 5 elements from (fibViaUnfold) : ${fibViaUnfold.take(5).toList}")


  // example 5.13
  val s1 = Stream.from(1).take(5)
  println("test mapViaUnfold")
  assert(s1.mapViaUnfold(_ + 1).toList == List(2, 3, 4, 5, 6))

  println("test takeViaUnfold")
  assert(s1.takeViaUnfold(3).toList == List(1, 2, 3))

  println("test takeWhileViaUnfold")
  assert(s1.takeWhileViaUnfold(_ < 4).toList == List(1, 2, 3))

  val s2 = "a" #:: "b" #:: "c" #:: "d" #:: empty[String]
  println("test zipAll")
  assert(s1.take(3).zipAll(s2).toList == List(
    (Some(1), Some("a")),
    (Some(2), Some("b")),
    (Some(3), Some("c")),
    (None, Some("d"))))


  // example 5.15
  println("test tails")
  println(s2.tails().map(_.toList).toList)


  println("test hasSubsequence")
  assert(s2.hasSubsequence("b" #:: "c" #:: empty[String]))
  assert(!s2.hasSubsequence("b" #:: "d" #:: empty[String]))



  println(s"tailsViaScanRight : ${s2.tailsViaScanRight.map(_.toList).toList}")






}
