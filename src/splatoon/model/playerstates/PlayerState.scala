package splatoon.model.playerstates

import splatoon.model.Player

abstract class PlayerState(player: Player) {

  def update(dt: Double): Unit = {

    if(player.leftKeyHeld){
      this.leftPressed()
    }
    if(player.rightKeyHeld){
      this.rightPressed()
    }
    if(player.upKeyHeld){
      this.upPressed()
    }
    if(player.downKeyHeld){
      this.downPressed()
    }
  }

  // API methods. Most methods do nothing by default. Only override methods that are needed for each state
  //KeyPressed
  def leftPressed(): Unit = {}

  def rightPressed(): Unit = {}

  def upPressed(): Unit = {}

  def downPressed(): Unit = {}

  def leftReleased(): Unit = {
    player.stop()
  }

  //KeyReleased()
  def rightReleased(): Unit = {
    player.stop()
  }

  def upReleased(): Unit = {
    player.stop()
  }

  def downReleased(): Unit = {
    player.stop()
  }

  def platformCollision(): Unit = {
    player.stop()
  }

}
