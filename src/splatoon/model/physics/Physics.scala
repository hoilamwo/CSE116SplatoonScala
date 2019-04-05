package splatoon.model.physics

import splatoon.model.Player
import splatoon.model.game_objects.{PhysicalObject}

object Physics {

  def computePotentialLocation(obj: PhysicalObject, dt: Double): PhysicsVector = {
    val newX = obj.location.x + dt * obj.velocity.x
    val newY = obj.location.y + dt * obj.velocity.y
    new PhysicsVector(newX, newY)
  }

  def updateWorld(world: World, deltaTime: Double): Unit = {

    for (player <- world.players) {

      // get potential location
      val potentialLocation = computePotentialLocation(player, deltaTime)

      // check collisions

      //update location
      player.location.x = potentialLocation.x
      player.location.y = potentialLocation.y

    }
  }
}
