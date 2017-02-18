package jason.ch5

import jason.ch5.Stream._

/**
  * Created by jason.kim on 2017. 2. 12..
  */
object Exercise5_3 extends App {

  // test example 5.4
  println()
  println("---------------- run forAll")
  val s4 = Stream.cons({println(1); 1}, Stream.cons({println(2); 2}, Stream.cons({println(3); 3}, Stream.empty[Int])))
  println(s4.forAll(_ > 1))

  val s5 = {println(1); 1} #:: {println(2); 2} #:: {println(3); 3} #:: Stream.empty[Int]
  println(s5.forAll(_ > 1))


  // test example 5.5
  println()
  println("---------------- run takeWhile")
  val s6 = {println(1); 1} #:: {println(2); 2} #:: {println(3); 3} #:: {println(-1); -1} #:: Stream.empty[Int]
  println(s6.takeWhileViaFoldRight(_ < 2).toList)
  assert(s6.takeWhileViaFoldRight(_ < 2).toList == List(1))



  // test example 5.6
  println()
  println("---------------- run headOption")
  val s7 = {println(1); 1} #:: {println(2); 2} #:: {println(3); 3} #:: Stream.empty[Int]
  assert(s7.headOption == Some(1))
  assert(Stream.empty[Int].headOption == None)


  // test example 5.7
  println()
  println("---------------- run map")
  val s8 = {println(1); 1} #:: {println(2); 2} #:: {println(3); 3} #:: Stream.empty[Int]
  assert(s8.map(_ + 1).toList == List(2, 3, 4))

  println()
  println("---------------- run filter")
  val s9 = {println(1); 1} #:: {println(2); 2} #:: {println(3); 3} #:: {println(-1); -1} #:: Stream.empty[Int]
  println(s9.filter(_ < 2).toList)

  println()
  println("---------------- run append")
  val s10 = {println(1); 1} #:: {println(2); 2} #:: {println(3); 3} #:: Stream.empty[Int]
  val s11 = {println(4); 4} #:: {println(5); 5} #:: Stream.empty[Int]
//  println(s10.append(s11))
  assert(s10.append(s11).toList == List(1, 2, 3, 4, 5))

  println()
  println("---------------- run flatMap")
  val s12 = {println(1); 1} #:: {println(2); 2} #:: {println(3); 3} #:: Stream.empty[Int]
  assert(s12.flatMap(a => Stream(a + 1)).toList == List(2, 3, 4))


}
