import com.alexandre.{GameTree, Board}
import org.scalatest.FunSuite

class GameTreeTest extends FunSuite {

  test("Best move when there is only one legal move should be this move") {
    val board = new Board(6, 0, 0, Array(0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0))//Array(13, 3, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0))
    val tree = GameTree(board)
    assert(tree.pickBestMove(1) == 2)
    println("***")
  }

  test("Children should be all null but one when there is only one legal move") {
    val board = new Board(6, 0, 0, Array(13, 3, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0))
    val tree = GameTree(board)
    assert(!tree.expanded)
    tree.expandChildren
    assert(tree.expanded)
    assert(tree.children.map(_ == null) sameElements Array(false, true, true, true, true, true))
  }

  test("Should have zero wins if starting from a losing configuration") {
    val board = new Board(6, 22, 23, Array(0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0))
    val tree = GameTree(board)
    tree.simulate(100)
    assert(tree.numWins == 0)
    assert(tree.numGames == 100)
  }

  test("Should have all wins if starting from a winning configuration") {
    val board = new Board(6, 20, 12, Array(0, 0, 0, 0, 0, 6, 5, 1, 1, 1, 1, 1))
    val tree = GameTree(board)
    tree.simulate(100)
    assert(tree.numWins == 100)
    assert(tree.numGames == 100)
  }

  test("Should pick the winning move if it exists") {
    val board = new Board(6, 17, 14, Array(5, 0, 0, 0, 0, 5, 0, 1, 1, 1, 1, 5))
    val tree = GameTree(board)
    val best = tree.pickBestMove(10000)
    assert(best == 5)
  }

  test("Should compute different scores for a starting board") {
    val board = new Board(6, 0, 0, Array.fill(12)(4))
    val tree = GameTree(board)
    val best = tree.pickBestMove(2000) // Maximum, think of reducing board size though
    //assert(best == 5)
  }

}
