package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.25 (p57)
object Exercies3_25 extends App {

  val left1 = Leaf(1)
  val right1 = Leaf(2)
  val branch1 = Branch(left1,right1)
  val left2 = Leaf(3)
  val right2 = Leaf(4)
  val branch2 = Branch(left2,right2)
  val root = Branch(branch1,branch2)

  println(Tree.size(left1)) // 1
  println(Tree.size(branch1)) // 1 + size(left1) + size(right1) = 3
  println(Tree.size(branch2)) // 1 + size(left2) + size(right2) = 3
  println(Tree.size(root)) // 1 + size(brancch1) + size(branch2) = 7
}
