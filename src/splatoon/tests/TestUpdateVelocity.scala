package splatoon.tests

import splatoon.model.game_objects.PhysicalObject
import org.scalatest._
import splatoon.model.physics.{Physics, PhysicsVector, World}

class TestUpdateVelocity extends FunSuite {

  val EPSILON: Double = 0.000001

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  def equalVectors(v1: PhysicsVector, v2: PhysicsVector): Boolean = {
    equalDoubles(v1.x, v2.x) && equalDoubles(v1.y, v2.y)
  }

  test("Physical objects fall") {
    val world: World = new World(10.0)
    val o1: PhysicalObject = new PhysicalObject(new PhysicsVector(5, 15), new PhysicsVector(5, 15))

    Physics.updateVelocity(o1, world, 1.0)
    assert(equalVectors(o1.velocity, new PhysicsVector(5, 5.0)))

    val o2: PhysicalObject = new PhysicalObject(new PhysicsVector(5, 0), new PhysicsVector(5, 15))
    Physics.updateVelocity(o2, world, 0.5)
    assert(equalVectors(o2.velocity, new PhysicsVector(5, 10.0)))

    val o3: PhysicalObject = new PhysicalObject(new PhysicsVector(5, 0), new PhysicsVector(5, -5))
    Physics.updateVelocity(o3, world, 0.1)
    assert(equalVectors(o3.velocity, new PhysicsVector(5, -6.0)))

  }

}
