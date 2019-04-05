package splatoon.model.playerstates

import splatoon.model.Player

class OnGround(player: Player) extends PlayerState(player) {

  //KeyPressed
  override def leftPressed(): Unit = {
    player.walkLeft()
    player.state = new Walking(player)
  }

  override def rightPressed(): Unit = {
    player.walkRight()
    player.state = new Walking(player)
  }

  override def upPressed(): Unit = {
    player.walkUp()
    player.state = new Walking(player)
  }

  override def downPressed(): Unit = {
    player.walkDown()
    player.state = new Walking(player)
  }

  //Don't need to worry about KeyReleased
}
