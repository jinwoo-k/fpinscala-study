package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.15 (p52)
object Exercies3_15 extends App {

  def concat[A](l: List[List[A]]): List[A] = List.foldRight(l, Nil:List[A])( List.foldRight(_,_)(Cons(_,_)) )

  val list = List(List(1,2,3),List(4,5,6),List(7,8,9))
  println(concat(list))
}
