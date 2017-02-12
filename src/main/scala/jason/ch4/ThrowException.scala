package jason.ch4

/**
  * Created by jason.kim on 2017. 2. 5..
  */
object ThrowException extends App {
  def failingFn(i: Int): Int = {
    lazy val y: Int = throw new Exception("fail!")
    try {
      val x = 42 + 5
      x + y
    } catch {
      case e: Exception => 43
    }
  }

  println(failingFn(23))
}
