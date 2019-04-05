package splatoon.model.playerstates

import splatoon.model.Player

class Walking(player: Player) extends OnGround(player) {

  //KeyPressed
  override def leftPressed(): Unit = {
    player.walkLeft()
  }

  override def rightPressed(): Unit = {
    player.walkRight()
  }

  override def upPressed(): Unit = {
    player.walkUp()
  }
  override def downPressed(): Unit = {
    player.walkDown()
  }

  //KeyReleased
  override def leftReleased(): Unit = {
    super.leftReleased()
  }

  override def rightReleased(): Unit = {
    super.rightReleased()
  }

  override def upReleased(): Unit = {
    super.upReleased()
  }

  override def downReleased(): Unit = {
    super.downReleased()
  }
}
