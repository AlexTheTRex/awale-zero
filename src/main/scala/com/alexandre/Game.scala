package com.alexandre

import android.view.View

// Bug in the endgame when i have to feed the computer

class Game (holder: WidgetHolder){

  var board = new Board(6, 0, 0, Array.fill(12)(4))

  var tree = new GameTree(board)
  tree.expandChildren

  val widgets = holder
  widgets.hideComputerBoard
  widgets.hidePlayerBoard
  widgets.renderCurrentBoard(board)
  widgets.computerPlayButton.setEnabled(false)

  var toPlay = 0

  def performPlayerMove(i: Int): Unit = {
    if (board.cups(i) > 0 && ((toPlay == 0 && i < 6))){
      tree = tree.children(i)
      widgets.renderPlayerBoard(board, i)
      board = board.play(i)
      widgets.renderCurrentBoard(board)
      widgets.showPlayerBoard
      widgets.log.setHeight(20)
      widgets.log.setVisibility(View.INVISIBLE)
      widgets.hideComputerBoard
      widgets.computerPlayButton.setEnabled(true)
      toPlay = 1 - toPlay
    }
  }

  def performComputerMove(): Unit = {
    val i = tree.pickBestMove(100)
    widgets.renderComputerBoard(board, (i + 6) % 12)
    widgets.showComputerBoard
    board = board.rotate
    board = board.playThenRotate(i)
    widgets.renderCurrentBoard(board)
    tree = tree.children(i)
    widgets.computerPlayButton.setEnabled(false)
    toPlay = 1 - toPlay
  }

  def setPlayListeners(): Unit = {
    widgets.computerPlayButton.setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performComputerMove
      }
    })
    widgets.currentBoard.cups(0).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(0)
      }
    })
    widgets.currentBoard.cups(1).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(1)
      }
    })
    widgets.currentBoard.cups(2).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(2)
      }
    })
    widgets.currentBoard.cups(3).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(3)
      }
    })
    widgets.currentBoard.cups(4).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(4)
      }
    })
    widgets.currentBoard.cups(5).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(5)
      }
    })/*
    widgets.currentBoard.cups(6).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(6)
      }
    })
    widgets.currentBoard.cups(7).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(7)
      }
    })
    widgets.currentBoard.cups(8).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(8)
      }
    })
    widgets.currentBoard.cups(9).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(9)
      }
    })
    widgets.currentBoard.cups(10).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(10)
      }
    })
    widgets.currentBoard.cups(11).setOnClickListener( new View.OnClickListener {
      override def onClick(view: View): Unit = {
        performPlayerMove(11)
      }
    })*/
  }

}
