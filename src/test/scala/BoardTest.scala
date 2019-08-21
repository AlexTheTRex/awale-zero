import com.alexandre.{Board, GameState}
import org.scalatest.FunSuite

class BoardTest extends FunSuite {

  test("Every move should be legal in a starting board") {
    val board = new Board(6, 0, 0, Array.fill(12)(4))
    val allLegal = (0 until 6).map(board.checkLegal).forall(_ == true)
    assert(allLegal)
  }
  test("Every move should be illegal in an unfeedable board") {
    val board = new Board(6, 0, 0, Array(1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0))
    val allIlegal = (0 until 6).map(board.checkLegal).forall(_ == false)
    assert(allIlegal)
  }
  test("checkLegal should work on an example") {
    val expected = Array(true, false, true, true, false, true, false, false, false, false, false, false)
    val board = new Board(6, 0, 0, Array(13, 3, 6, 3, 1, 1, 0, 0, 0, 0, 0, 0))
    val result = (0 until 12).map(board.checkLegal)
    assert(expected sameElements result)
  }

  test("checkLegal should work on another example") {
    val expected = Array(false, false, false, false, false, true, false, false, false, false, false, false)
    val board = new Board(6, 0, 0, Array(0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0))
    val result = (0 until 12).map(board.checkLegal)
    assert(expected sameElements result)
  }

  test("checkGameOver should work on an example") {
    val board = new Board(6, 17, 14, Array(5, 0, 0, 0, 0, 5, 0, 1, 1, 1, 1, 5))
    val playedBoard = board.playThenRotate(5)
    val result = playedBoard.checkGameOver()
    assert(result == GameState.NORTH_WINS)
  }


}