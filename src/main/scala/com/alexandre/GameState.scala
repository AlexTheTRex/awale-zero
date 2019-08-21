package com.alexandre

object GameState extends Enumeration {
  type GameState = Value
  val SOUTH_WINS, NORTH_WINS, DRAW, ONGOING = Value
}
