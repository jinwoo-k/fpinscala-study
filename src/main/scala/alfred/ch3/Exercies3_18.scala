package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.18 (p53)
object Exercies3_18 extends App {

  def map[A,B](as: List[A])(f: A => B) : List[B] = List.foldRight2(as,Nil: List[B])( (a, g) => Cons(f(a), g))

  val list = List(1, 2, 3)
  println(map(list)( _ + 1 ))
  println(map(list)( _ * 2 ))
}
