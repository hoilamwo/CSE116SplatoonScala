package splatoon.model.playerstates

import splatoon.model.Player

class GameOver(player: Player) extends PlayerState(player) {

  //Key Pressed
  override def leftPressed(): Unit = {}

  override def rightPressed(): Unit = {}

  override def upPressed(): Unit = {}

  override def downPressed(): Unit =  {}

  //Key Released
  override def leftReleased(): Unit = {}

  override def rightReleased(): Unit = {}

  override def upReleased(): Unit = {}

  override def downReleased(): Unit = {}

}
