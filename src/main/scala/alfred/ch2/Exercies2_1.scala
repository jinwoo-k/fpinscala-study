package alfred.ch2

/**
 * Created by alfredkim on 2017. 1. 16..
 */
object Exercies2_1 extends App {

  /*  연습문제 2-1 (p27) */

  // 꼬리재귀가 아닌 정의에 가장 가까운 구현
  def fib1( n : Int) : Int = n match {
    case 0 | 1 => n
    case _ => fib1( n-1 ) + fib1( n-2 )
  }

  // 루프 형식으로 구현
  def fib2( n : Int ) : Int = {
    var a = 0
    var b = 1
    var i = 0
    while( i < n ) {
      val c = a + b
      a = b
      b = c
      i = i + 1
    }
    a
  }

  // 스트림을 이용한 구현 1
  val fib3 = Stream.iterate( (0 ,1) ) { case (a,b)=>(b,a+b)  }.map(_._1)

  // 스트림을 이용한 구현 2
  def fib4( n : Int ) : Int = {
    def go(f0: Int, f1: Int) : Stream[Int] = {
      Stream.cons(f0,go(f1,f0+f1))
    }
    val fibonachi = go(0,1)
    fibonachi(n)
  }

  // 꼬리 재귀 형식
  def fib5( n : Int) : Int = {
    @annotation.tailrec
    def fib_tail( n: Int, a:Int, b:Int): Int = n match {
      case 0 => a
      case _ => fib_tail( n-1, b, a+b )
    }
    fib_tail( n, 0, 1)
  }


  def test[R](msg:String)(block: => R): R = {
    val t0 = System.nanoTime()
    val result = block
    println(s"[$msg]")
    println("Elapsed time: " + (System.nanoTime - t0) + "ns")
    println(s"result=$result")
    result
  }

  test("fib1 : 정의에 가까운 재귀호출")(fib1(46))
  test("fib2 : 반복문 사용")(fib2(46))
  test("fib3 : 무한스트람1 - iterator")(fib3(46))
  test("fib4 : 무한스트림2 - cons")(fib4(46))
  test("fib5 : 꼬리재귀 호출")(fib5(46))

}
