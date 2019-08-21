package com.alexandre

import android.content.res.Resources
import android.widget.{Button, LinearLayout}

case class BoardWidget(resources: Resources, packageName: String,
                       topLayout: LinearLayout, bottomLayout: LinearLayout,
                       button0: Button, button1: Button, button2: Button, button3: Button,
                       button4: Button, button5: Button, button6: Button, button7: Button,
                       button8: Button, button9: Button, button10: Button, button11: Button) {

  val cups: Array[android.widget.Button] = Array(
    button0, button1, button2, button3, button4, button5,
    button6, button7, button8, button9, button10, button11
  )
  val yellowCircleIcon = resources.getDrawable(
    resources.getIdentifier("yellow", "drawable", packageName)
  )
  val redCircleIcon = resources.getDrawable(
    resources.getIdentifier("red", "drawable", packageName)
  )
  val whiteCircleIcon = resources.getDrawable(
    resources.getIdentifier("circle", "drawable", packageName)
  )

  def render(board: Board): Unit = {
    for (i <- 0 until board.nCups){
      cups(i).setBackground(whiteCircleIcon)
      cups(i).setText(board.cups(i).toString)
    }
  }

  def render(board: Board, playedCupIndex: Int): Unit = {
    for (i <- 0 until board.nCups){
      cups(i).setBackground(whiteCircleIcon)
      cups(i).setText(board.cups(i).toString)
    }
    cups(playedCupIndex).setBackground(yellowCircleIcon)
  }

  def activateButtons(): Unit = {
    cups.foreach{_.setEnabled(true)}
  }

  def deActivateButtons(): Unit = {
    cups.foreach{_.setEnabled(false)}
  }

}
