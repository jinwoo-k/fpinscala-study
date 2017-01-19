package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.19 (p53)
object Exercies3_19 extends App {

  def filter[A](as: List[A])( f: A => Boolean) : List[A] =
    List.foldRight2(as,Nil: List[A])( (a, g) => if(f(a)) Cons(a,g) else g )

  val list = List(1,2,3,4,5,6,7,8,9,10)
  val even = filter(list)( _ % 2 == 0 )
  println(even)
}
