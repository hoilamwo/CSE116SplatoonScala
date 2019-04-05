package splatoon.model

import splatoon.model.physics.{Boundary, Physics, PhysicsVector, World}

class Game {

  val world = new World(10)

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
  val Top:Boundary= new Boundary(new PhysicsVector(100,100),new PhysicsVector(400,100))
  val Bottom:Boundary=new Boundary(new PhysicsVector(100,400),new PhysicsVector(400,400))

  val Right:Boundary=new Boundary(new PhysicsVector(400,100),new PhysicsVector(400,400))
  val Left:Boundary= new Boundary(new PhysicsVector(100,100), new PhysicsVector(100,400))
  val map:List[Boundary] = List(Top,Bottom,Right,Left)
  world.boundaries = map

}
