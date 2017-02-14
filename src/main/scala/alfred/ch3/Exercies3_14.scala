package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.14 (p52)
object Exercies3_14 extends App {

  def append1[A](a1: List[A], a2: List[A]): List[A] = List.foldRight(a1,a2)(Cons(_,_))

  println(append1(List(1,2,3),List(4,5,6)))

  def append2[A](a1: List[A], a2: List[A]): List[A] =
    List.foldLeft(a1, (b: List[A]) => b)((g, a) => b => g(Cons(a, b)))(Nil)

  println(append2(List(1,2,3),List(4,5,6)))
}
