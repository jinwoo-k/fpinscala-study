package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.28 (p57)
object Exercies3_29 extends App {



  val left1 = Leaf(1)
  val right1 = Leaf(2)
  val branch1 = Branch(left1,right1)
  val left2 = Leaf(3)
  val right2 = Leaf(4)
  val branch2 = Branch(left2,right2)
  val root = Branch(branch1,branch2)

  println(Tree.size(root))
  println(Tree.depth(root))
  println(Tree.maximum(root))
  println(Tree.map(root)(_ * 10))
  println()
  println(Tree.size1(root))
  println(Tree.depth1(root))
  println(Tree.maximum1(root))
  println(Tree.map1(root)(_ * 10))
}
