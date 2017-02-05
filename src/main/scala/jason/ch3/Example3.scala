package jason.ch3

/**
  * Created by jason.kim on 2017. 1. 30..
  */
object Example3 extends App {
  val l = List(1, 2, 3, 4, 5)

  def exampe3_1 = {
    val x = l match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + List.sum(t)
      case _ => 101
    }

    assert(x == 3)
  }
  exampe3_1

  def example3_2 = {
    assert(l.tail == List(2, 3, 4, 5))
  }
  example3_2

  def example3_3 = {
    assert(l.setHead(100) == List(100, 2, 3, 4, 5))
  }
  example3_3

  def example3_4 = {
    assert(List.drop(l, 3) == List(4, 5))
  }
  example3_4

  def example3_5 = {
    assert(List.dropWhile(l)(_ < 3) == List(3, 4, 5))
  }
  example3_5

  def example3_6 = {
    assert(List.init(l) == List(1, 2, 3, 4))
  }
  example3_6

  def list3_2 = {
    assert(List.sum2(l) == 15)

    val l2 = List(1d, 2d, 3d, 4d, 5d)
    assert(List.product2(l2) == 120d)
  }
  list3_2

  /**
    * example 3-7
    * foldRight / foldLeft 는 재귀호출의 종단조건이 Nil 이 나타날때까지 이므로, 중간에 순회를 멈추지 못한다.
    */

  def example3_8 = {
    val l2 = List.foldRight(l, Nil: List[Int])(Cons(_, _))
    assert(l == l2)
  }
  example3_8

  def example3_9 = {
    assert(List.length(l) == 5)
  }
  example3_9

  def example3_10 = {
    def getLongList(l: List[Int], n: Int): List[Int] = if (n > 0) {
      getLongList(Cons(n, l), n - 1)
    } else {
      l
    }

    val longList = getLongList(Nil: List[Int], 10000)

    try {
      List.length(longList)
      assert(false, "Should throw stackOverflowException")
    } catch {
      case e: Exception =>
    }

    List.length2(longList)
  }
  example3_10

  def example3_12 = {
    assert(List.reverse(l) == List(5, 4, 3, 2, 1))
  }
  example3_12

  def example3_14 = {
    val l1 = List(1, 2, 3)
    val l2 = List(4, 5, 6)
    assert(List.append2(l1, l2) == List(1, 2, 3, 4, 5, 6))
  }
  example3_14

  def example3_15 = {
    val ll = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    assert(List.flatten(ll) == List(1, 2, 3, 4, 5, 6, 7, 8, 9))
  }
  example3_15

  def example3_16 = {
    assert(List.plusOne(l) == List(2, 3, 4, 5, 6))
  }
  example3_16

  def example3_17 = {
    val l2 = List(1d, 2d, 3d, 4d, 5d)
    assert(List.toString(l2) == List("1.0", "2.0", "3.0", "4.0", "5.0"))
  }
  example3_17

  def example3_18 = {
    val l2 = List(1d, 2d, 3d, 4d, 5d)
    assert(List.map(l2)(_.toString) == List("1.0", "2.0", "3.0", "4.0", "5.0"))
  }
  example3_18

  def example3_19 = {
    def filterList = List.filter(l)(_)
    filterList(_ > 3)

    assert(List.filter(l)(_ > 3) == List(4, 5))
    assert(List.filter2(l, (i: Int) => i > 3) == List(4, 5))
  }
  example3_19

  def example3_20 = {
    assert(List.flatMap(l)(i => List(i, i)) == List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5))
  }
  example3_20

  def example3_21 = {
    assert(List.filterViaFlatMap(l)(_ > 3) == List(4, 5))
  }
  example3_21

  def example3_22 = {
    val l1 = List(1, 2, 3, 4, 5)
    val l2 = List(4, 5, 6)
    assert(List.add(l1, l2) == List(5, 7, 9, 4, 5))
  }
  example3_22

  def example3_23 = {
    val l1 = List(1, 2, 3)
    val l2 = List(4, 5, 6)
    assert(List.zipWith(l1, l2)(_ + _) == List(5, 7, 9))
  }
  example3_23

  def example3_24 = {
    val l1 = List(1, 2, 3, 4, 5, 6, 7)
    val l2 = List(4, 5, 6)
    val l3 = List(4, 5, 8)
    assert(List.hasSubsequence(l1, l2))
    assert(!List.hasSubsequence(l1, l3))
  }
  example3_24


  val t = Branch(Branch(Leaf(1), Leaf(3)), Leaf(10))
  def example3_25 = {
    assert(t.size == 5)
  }
  example3_25

  def example3_26 = {
    assert(Tree.maximum(t) == 10)
  }
  example3_26

  def example3_27 = {
    assert(t.depth == 3)
  }
  example3_27

  def example3_28 = {
    assert(t.map(_ + 1) == Branch(Branch(Leaf(2), Leaf(4)), Leaf(11)))
  }
  example3_28

  def example3_29 = {
    assert(t.depthViaFold == 3)
    assert(t.sizeViaFold == 5)
    assert(Tree.maximum(t) == 10)
  }
  example3_29


}
