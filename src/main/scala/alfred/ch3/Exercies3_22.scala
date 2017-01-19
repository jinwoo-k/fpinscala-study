package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.22 (p54)
object Exercies3_22 extends App {

  def addPair(as1: List[Int], as2: List[Int]) : List[Int] = (as1, as2) match {
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, addPair(t1,t2) )
    case _ => Nil
  }

  println("-- addPair -- ")
  println(addPair(Nil,Nil))
  println(addPair(List(1, 2, 3),Nil))
  println(addPair(Nil, List(1, 2, 3)))
  println(addPair(List(1, 2, 3), List(10, 20, 30)))
  println(addPair(List(1, 2, 3, 4), List(10, 20, 30)))
  println(addPair(List(1, 2, 3), List(10, 20, 30, 40)))
}
