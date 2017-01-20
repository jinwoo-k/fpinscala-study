package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.26 (p57)
object Exercies3_26 extends App {

  val left1 = Leaf(1)
  val right1 = Leaf(2)
  val branch1 = Branch(left1,right1)
  val left2 = Leaf(3)
  val right2 = Leaf(4)
  val branch2 = Branch(left2,right2)
  val root = Branch(branch1,branch2)

  println(Tree.maximum(left1)) // 1
  println(Tree.maximum(branch1)) // 2
  println(Tree.maximum(branch2)) // 4
  println(Tree.maximum(root)) // 4
}
