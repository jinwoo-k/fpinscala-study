package jason.ch4

/**
  * Created by jason.kim on 2017. 2. 5..
  */
object Example4 extends App {
  import Option._
  def example4_2 = {
    assert(None == variance(Seq.empty[Double]))
    assert(Some(0.0) == variance(Seq(1)))
    assert(Some(1.0) == variance(Seq(1,3)))
  }
  example4_2

  def example4_4 = {
    assert(sequence(List(Some(1), Some(2), Some(3))) == Some(List(1, 2, 3)))
    assert(sequence(List(Some(1), None, Some(3))) == None)
  }
  example4_4


}
