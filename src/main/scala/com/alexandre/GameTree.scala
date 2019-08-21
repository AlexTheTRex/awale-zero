package com.alexandre

import java.lang.StackOverflowError

import com.alexandre.GameState.GameState

import scala.math._

case class GameTree(board: Board) {
  val explorationParam = 1.4142f // Experiment

  var numGames = 1
  var numWins = 0
  var expanded = false
  val children: Array[GameTree] = Array.fill(board.cupsPerPlayer)(null)

  def pickBestMove(nSimu: Int = 1000): Int = {
    try {
      simulate(nSimu)
    } catch {
      case e: OutOfMemoryError =>
    }
    pickBestChild
  }

  def expandChildren(): Unit = {
    (0 until board.cupsPerPlayer)
      .filter(board.checkLegal)
      .foreach { i => children(i) = new GameTree(board.playThenRotate(i)) }
    expanded = true
  }

  def computeUCB(): Array[Float] =
    children
      .map{
        case null => -1f
        case c => c.numWins.toFloat / c.numGames.toFloat +
          explorationParam * sqrt(log(numGames) / c.numGames).toFloat
      }

  def computeArgMax(data: Array[Float]): Int = {
    var argmax = 0
    var max = data(0)
    for (i <- 1 until board.cupsPerPlayer){
      if (data(i) > max){
        argmax = i
        max = data(i)
      }
    }
    argmax
  }

  def pickBestChild(): Int = { // Why no argmax in collections :(
    val scores = children.map{
      case null => -1
      case c => c.numGames.toFloat}
    println(scores.mkString("_"))
    computeArgMax(scores)
  }

  def pickUcbChild(): Int = { // Why no argmax in collections :(
    val ucb = computeUCB
    //println(ucb.mkString("__"))
    computeArgMax(ucb)
  }

  def simulateOnce(): Int = {
    // Expand if not already done
    if (!expanded)
      expandChildren
    // Check if leaf node
    val state = board.checkGameOver(children.count(_ != null) == 0)
    if (state == GameState.NORTH_WINS){
      numGames += 1
      numWins += 1
      1
    }
    else if (state == GameState.SOUTH_WINS  || state == GameState.DRAW){
      numGames += 1
      0
    }
    else {
      // Recursively perform search and backpropagate

      //println("Chosen child is : " + pickUcbChild)
      //println("chosen child is null : " + (chosenChild == null).toString + " ucb is : " + computeUCB.mkString(","))
      //println(board.cups.mkString(" "))
      try {
        val winOrLose = children(pickUcbChild).simulateOnce
        numGames += 1
        numWins += 1 - winOrLose
        1 - winOrLose
      } catch {
        case e: StackOverflowError => { // Treat a too long game as a loss
          numGames += 1
          1
        }
      }

    }
  }

  def simulate(nSimu: Int): Unit =
    for (i <- 1 to nSimu) {
      simulateOnce
      //println(children.filter(_ != null).map(_.numGames).mkString(" ") + " / " + children.filter(_ != null).map(_.numWins).mkString(" "))
      //println("Tree size : " + getSize)
    }

  def getSize(): Int = {
    children.filter(_ != null).map(_.getSize).sum + 1
  }
}

