package com.alexandre

import com.alexandre.GameState.GameState


case class Board(cupsPerPlayer: Int, scoreSouth: Int, scoreNorth: Int, cups: Array[Int]) {
  // By convention the board is seen from South
  // Cups are numbered counterclockwise from bottom left
  // Cups 0 through 5 are South's, 6 through 11 are North's
  val nCups = 2 * cupsPerPlayer

  // The number of seeds taken by each player
  //var scoreSouth: Int = 0
  //var scoreNorth: Int = 0
  // The cups
  //val cups: Array[Int] = Array.fill(nCups)(4)


  def checkLegal(index: Int): Boolean ={
    //assert(index < cupsPerPlayer)
    if (cups(index) == 0)
      false
    else if (cups.slice(cupsPerPlayer, 2 * cupsPerPlayer).sum == 0 && cups(index) < cupsPerPlayer - index)
      false
    else
      true
  }

  def checkGameOver(noLegalMoves: Boolean = false): GameState = {
    // If the flag that no legal moves remain is passed, we look at current scores
    if (noLegalMoves)
      if (scoreSouth == scoreNorth)
        GameState.DRAW
      else if (scoreSouth > scoreNorth)
        GameState.SOUTH_WINS
      else
        GameState.NORTH_WINS
    // Otherwise we check whether we have an early win
    else if (scoreSouth > 4 * cupsPerPlayer)
      GameState.SOUTH_WINS
    else if (scoreNorth > 4 * cupsPerPlayer)
      GameState.NORTH_WINS
    else
      GameState.ONGOING
  }

  def play(index: Int): Board = {
    assert(index < 6 && index >= 0)
    // Create new board and sow the seeds
    val newBoard = new Board(cupsPerPlayer, scoreSouth, scoreNorth, cups.clone)

    newBoard.cups(index) = 0
    var i = index
    var nSeeds = cups(index)
    while (nSeeds > 0){
      i = (i + 1) % nCups
      if (i % nCups != index){
        newBoard.cups(i) += 1
        nSeeds -= 1
      }
    }
    // See if any seeds are picked up
    var takenSeeds = 0
    while ((newBoard.cups(i) == 2 || newBoard.cups(i) == 3) && i >= 6){
      takenSeeds += newBoard.cups(i)
      newBoard.cups(i) = 0
      i = (i - 1 + nCups) % nCups
    }
    if (index < 6)
      newBoard.copy(scoreSouth = scoreSouth + takenSeeds)
    else
      newBoard.copy(scoreNorth = scoreNorth + takenSeeds)
  }

  def rotate(): Board = {
    val newBoard = new Board(cupsPerPlayer, scoreNorth, scoreSouth, cups.clone)
    (0 until nCups)
      .foreach{i =>
        newBoard.cups(i) = cups((i + cupsPerPlayer) % nCups)
      }
    newBoard
  }

  def playThenRotate(index: Int): Board = play(index).rotate

  override def toString: String = scoreSouth.toString + " " + scoreNorth.toString + " " + cups.mkString("-")
}
