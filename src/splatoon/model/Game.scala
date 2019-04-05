package splatoon.model

import splatoon.model.physics.{Physics, PhysicsVector, World}

class Game {

  val world = new World()

  val gridWidth: Double = 20
  val gridHeight: Double = 20
  val playerSize: Double = 0.3


  val player = new Player(
    new PhysicsVector(gridWidth / 2.0, gridHeight / 2.0),
    new PhysicsVector(0, 0)
  )

  world.players = List(player)

  def update(deltaTime: Double): Unit = {
    Physics.updateWorld(this.world, deltaTime)
    player.update(deltaTime)
  }

}
