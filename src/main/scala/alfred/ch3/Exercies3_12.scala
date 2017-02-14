package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.12 (p52)
object Exercies3_12 extends App {

  //  foldLeft 사용
  def reverse1[A](l: List[A]) : List[A] = List.foldLeft(l,Nil:List[A])((r,a) => Cons[A](a,r))

  // 연습문제 3.13 참고 ( foldLeft를 foldRight로 구현하는 방법을 이용 )
  def reverse2[A](l: List[A]) : List[A] = List.foldRight(l, (b: List[A]) => b)((a,g) => b => g(Cons(a,b)))(Nil)

  val list = List(1,2,3)

  println(reverse1(Nil))
  println(reverse1(list))

  println(reverse2(Nil))
  println(reverse2(list))

}
