package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.27 (p57)
object Exercies3_27 extends App {

  val left1 = Leaf(1)
  val right1 = Leaf(2)
  val branch1 = Branch(left1,right1)
  val left2 = Leaf(3)
  val right2 = Leaf(4)
  val branch2 = Branch(left2,right2)
  val root = Branch(branch1,branch2)

  println(Tree.depth(left1)) // 0
  println(Tree.depth(root)) // 1 + max(1,1) = 2
}
