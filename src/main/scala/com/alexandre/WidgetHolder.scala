package com.alexandre

import android.view.View
import android.widget.{Button, LinearLayout, TextView}

case class WidgetHolder(
                        log: TextView,
                        scores: TextView,
                        youPlayedBoard: BoardWidget,
                        computerPlayedBoard: BoardWidget,
                        currentBoard: BoardWidget,
                        computerPlayButton: Button,
                        youPlayed: TextView,
                        topCupsYouPlayed: LinearLayout,
                        bottomCupsYouPlayed: LinearLayout,
                        computerPlayed: TextView,
                        topCupsComputerPlayed: LinearLayout,
                        bottomCupsComputerPlayed: LinearLayout) {


  def renderCurrentBoard(board: Board): Unit = {
    currentBoard.render(board)
    for (i <- 0 until board.nCups)
    scores.setText("Player : " + board.scoreSouth + "    CPU : " + board.scoreNorth)
  }

  def renderPlayerBoard(board: Board, playedCupIndex: Int): Unit = {
    youPlayedBoard.render(board, playedCupIndex)
  }

  def renderComputerBoard(board: Board, playedCupIndex: Int): Unit = {
    computerPlayedBoard.render(board, playedCupIndex)
  }

  def hidePlayerBoard(): Unit = {
    youPlayed.setVisibility(View.INVISIBLE)
    topCupsYouPlayed.setVisibility(View.INVISIBLE)
    bottomCupsYouPlayed.setVisibility(View.INVISIBLE)
  }

  def showPlayerBoard(): Unit = {
    youPlayed.setVisibility(View.VISIBLE)
    topCupsYouPlayed.setVisibility(View.VISIBLE)
    bottomCupsYouPlayed.setVisibility(View.VISIBLE)
  }

  def hideComputerBoard(): Unit = {
    computerPlayed.setVisibility(View.INVISIBLE)
    topCupsComputerPlayed.setVisibility(View.INVISIBLE)
    bottomCupsComputerPlayed.setVisibility(View.INVISIBLE)
  }

  def showComputerBoard(): Unit = {
    computerPlayed.setVisibility(View.VISIBLE)
    topCupsComputerPlayed.setVisibility(View.VISIBLE)
    bottomCupsComputerPlayed.setVisibility(View.VISIBLE)
  }

}
