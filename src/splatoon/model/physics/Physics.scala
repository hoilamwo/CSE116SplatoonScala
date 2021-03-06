package splatoon.model.physics

import splatoon.model.Player
import splatoon.model.game_objects.{PhysicalObject}
import java.awt.geom.{Line2D, Point2D}
object Physics {
  def ~=(x: Double, y: Double, precision: Double) = {
    if ((x - y).abs < precision)
      true
    else {
      false
    }
  }
  def computePotentialLocation(obj: PhysicalObject, dt: Double): PhysicsVector = {
    val newX = obj.location.x + dt * obj.velocity.x
    val newY = obj.location.y + dt * obj.velocity.y
    new PhysicsVector(newX, newY)
  }
  def updateVelocity(obj: PhysicalObject, world: World, dt: Double): Unit = {
    // Always apply gravity. Objects will adjust their velocity if they are on the ground
    obj.velocity.y = obj.velocity.y - world.gravity * dt
  }
  def detectCollision(po: PhysicalObject, potentialLocation: PhysicsVector, boun: Boundary): Boolean = {
    val poX:Double = po.location.x
    val poY:Double = po.location.y
    val poLocationX: Double = potentialLocation.x
    val poLocationY: Double = potentialLocation.y

    val bounStartX: Double = boun.start.x
    val bounStartY: Double = boun.start.y
    val bounEndX: Double = boun.end.x
    val bounEndY: Double = boun.end.y

    var check:Boolean = Line2D.linesIntersect(poX, poY, poLocationX, poLocationY, bounStartX, bounStartY, bounEndX, bounEndY)

    if(poLocationX >= bounStartX || poLocationX <= bounEndX){
      val AB: Double = Point2D.distance(bounStartX, bounStartY, bounEndX, bounEndY)
      val AC: Double = Point2D.distance(bounStartX, bounStartY, poLocationX, poLocationY)
      val CB: Double = Point2D.distance(poLocationX, poLocationY, bounEndX, bounEndY)
      if(~=((AC+CB), AB, 0.0001)){
        check = false
      }
    }
    if(bounEndX >= poX || bounEndX <= poLocationX){
      val AB: Double = Point2D.distance(poX, poY, poLocationX, poLocationY)
      val AC: Double = Point2D.distance(poX, poY, bounEndX, bounEndY)
      val CB: Double = Point2D.distance(bounEndX, bounEndY, poLocationX, poLocationY)
      if(~=((AC+CB), AB, 0.0001)) {
        check = false
      }
    }
    check
  }
  def updateWorld(world: World, deltaTime: Double): Unit = {

    for (player <- world.players) {

      // get potential location
      val potentialLocation = computePotentialLocation(player, deltaTime)

      // check collisions
      var collisionDetected = false
      for (wall <- world.boundaries) {
        if (detectCollision(player, potentialLocation, wall)) {
          collisionDetected = true
        }
      }
      //update location
        if(!collisionDetected || potentialLocation.x>player.location.x) {
          player.location.x = potentialLocation.x
        }
        if(!collisionDetected || potentialLocation.y>player.location.y){
          player.location.y = potentialLocation.y
        }

    }
  }
}
