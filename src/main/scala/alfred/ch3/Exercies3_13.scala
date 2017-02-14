package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.13 (p52)
object Exercies3_13 extends App {

  def foldRightWithFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B =
    List.foldLeft(l, (b:B) => b)((g,a) => b => g(f(a,b)))(z)
  // list = List(a0,a1,a2)
  // g0 : b => b
  // g1 : b => g0(f(a0,b))
  // g2 : b => g1(f(a1,b))
  // g3 : b => g2(f(a2,b))
  //      b => g1(f(a1,f(a2,b))
  //      b => g0(f(a0,f(a1,f(a2,b))))
  //      b => f(a0,f(a1,f(a2,b)))
  // g3(z)  : f(a0,f(a1,f(a2,z))

  def foldLeftWithFoldRight[A,B](l: List[A], z: B)(f: (B,A) => B): B =
    List.foldRight(l, (b:B) => b)((a,g) => b => g(f(b,a)))(z)
  // list = List(a0,a1,a2)
  // g0 : b => b
  // g1 : b => g0(f(b,a2))
  // g2 : b => g1(f(b,a1))
  // g3 : b => g2(f(b,a0))
  //      b => g1(f(f(b,a0),a1))
  //      b => g0(f(f(f(b,a0),a1),a2))
  //      b => f(f(f(b,a0),a1),a2)
  // g3(z)  : f(f(f(z,a0),a1),a2)






  val list = List("a","b","c")
  val res1 = List.foldRight       (list,"")( (a,g) => g + a )
  val res2 = foldRightWithFoldLeft(list,"")( (a,g) => g + a )
  val res3 = List.foldLeft        (list,"")( (g,a) => g + a )
  val res4 = foldLeftWithFoldRight(list,"")( (g,a) => g + a )

  println(res1)
  println(res2)

  println(res3)
  println(res4)

  // 연습문제 3.10 참조 foldRight2는 foldLeft를 사용하므로 StackOverflow 발생하지 않는다!
  val longList = (1 to 10000).foldRight(Nil:List[Int])(Cons(_,_))
  val sum = List.foldRight2(longList,0)( (a,g) => g + a )
  println(s"sum=$sum")

}
