package jason.ch5

/**
  * Created by jason.kim on 2017. 2. 12..
  */
object Exercise5_2 extends App {
  // test example 5.1 ~ 5.3
//  assert(Stream(1, 2, 3).take(2).toList == List(1, 2))
//  assert(Stream(1, 2, 3).drop(2).toList == List(3))
//  assert(Stream(1, 2, 3).takeWhile(_ < 3).toList == List(1, 2))

//  val s = Stream.apply(() => {println(1); 1}, () => {println(2); 2}, () => {println(3); 3})
//  println(s.exists(i => i() > 0))

//  val s2 = Stream.cons({println(1); 1}, Stream.cons({println(2); 2}, Stream.cons({println(3); 3}, Stream.empty[Int])))
//  println(s2.exists(_ > 0))
//
  val s3 = Stream.apply({println(1); 1}, {println(2); 2}, {println(3); 3})
  println(s3.exists(_ > 0))



}
