package alfred.ch3
/**
 * Created by alfredkim on 2017. 1. 18..
 */
object ListVsVector extends App {

  import Numeric.Implicits._
  def mean[T: Numeric](xs: Iterable[T]): Double = xs.sum.toDouble / xs.size
  def variance[T: Numeric](xs: Iterable[T]): Double = {
    val avg = mean(xs)
    xs.map(_.toDouble).map(a => math.pow(a - avg, 2)).sum / xs.size
  }
  def stdDev[T: Numeric](xs: Iterable[T]): Double = math.sqrt(variance(xs))


  // p47 참조
  val list1: scala.collection.immutable.List[Int] = (1 to 10).toList
  val list2: scala.collection.immutable.List[Int] = (1 to 1000000).toList
  val vector1 = (1 to 10).toVector
  val vector2 = (1 to 1000000).toVector

  def run[R](block: => R): Double = {
    val t0 = System.nanoTime()
    block
    (System.nanoTime - t0)
  }


  val actionList: Seq[String] = Seq("head", "tail", "init", "access","append end","append start")
  val data = for {
    i <- 1 to 300
    action <- actionList
  } yield {
      val times = action match {
        case "head" =>
          (run(list2.head) - run(list1.head), run(vector2.head) - run(vector1.head))
        case "tail" =>
          (run(list2.tail) - run(list1.tail), run(vector2.tail) - run(vector1.tail))
        case "init" =>
          (run(list2.init) - run(list1.init), run(vector2.init) - run(vector1.init))
        case "access" =>
          (run(list2(99999)) - run(list2(0)), run(vector2(99999)) - run(vector2(0)))
        case "append end" =>
          (run(list2 :+ 1) - run(list1 :+ 1), run(vector2 :+ 1) - run(vector1 :+ 1))
        case "append start" =>
          (run(1 +: list2) - run(1 +: list1), run(1 +: vector2) - run(1 +: vector1))
      }
      (i, action, (times._1,times._2))
  }
  data.filter{ case (idx, _, _) => idx > 1 }
    .map {
      case (_, action,times) => (action,times)
    }
    .groupBy(_._1).mapValues(_.map { case (act,(lt,vt)) => (lt,vt)}.unzip)
    .foreach { case (act,(lt,vt)) =>
      println(s"$act\n\tstd(list):${stdDev(lt).toInt} ns \n\tstd(vector):${stdDev(vt).toInt} ns")
    }
}
