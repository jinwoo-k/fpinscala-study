package jason.ch5

/**
  * Created by jason.kim on 2017. 2. 12..
  */
object Exercise5_1 extends App {
  // && or || 의 나태성 테스트
  println(false && {println("!!"); true})
  println(true || {println("!!"); false})



  // thunk를 이용한 평가 지연
  def if2[A](cond: Boolean, onTrue: () => A, onFalse: () => A): A =
    if (cond) onTrue() else onFalse()

  val a = 20
  println(if2(a < 22,
    () => { println("a"); 100 },
    () => { println("b"); 101 }))



  // thunk 의 간편식
  def simpleIf2[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
    if (cond) onTrue else onFalse

  val b = 23
  println(
    simpleIf2(b < 22,
      { println("a"); 100 },
      { println("b"); 101 }))



  // lazy 키워드를 활용한 memoization
  def maybeTwice(b: Boolean, i: => Int) = {
    if (b) i + i else 0
  }

  def maybeTwice2(b: Boolean, i: => Int) = {
    lazy val j = i
    if (b) j + j else 0
  }

  println(maybeTwice(true, {println("calc1"); 100}))
  println(maybeTwice2(true, {println("calc2"); 100}))




  // Stream memoization test
  def measureTime[A](name: String, job : => A): A = {
    val st = System.currentTimeMillis()
    val a = job
    println(s"$name 작업을 수행하는데 걸린 시간 : ${System.currentTimeMillis() - st}ms")
    a
  }
  val x = Cons(() => {Thread.sleep(1000); 1}, () => Empty)
  measureTime("without memoization", {x.headOption; x.headOption})

  val y = Stream.cons({Thread.sleep(1000); 1}, Empty)
  measureTime("with memoization", {y.headOption; y.headOption})




}
