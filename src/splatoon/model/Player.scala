package splatoon.model

import splatoon.model.game_objects.PhysicalObject
import splatoon.model.physics._
import splatoon.model.playerstates.{OnGround, PlayerState}

class Player(playerLocation: PhysicsVector, playerVelocity: PhysicsVector) extends PhysicalObject(playerLocation, playerVelocity) {

  val walkingSpeed: Double = 4.0

  var state: PlayerState = new OnGround(this)

  // Held flags used to improve responsiveness. Without them the player has to either repress the button or
  // wait for the next button pressed event (which is slow)
  var leftKeyHeld = false
  var rightKeyHeld = false
  var upKeyHeld = false
  var downKeyHeld = false

  // Begin API methods
  def leftPressed(): Unit = {
    this.leftKeyHeld = true
    this.state.leftPressed()
  }

  def rightPressed(): Unit = {
    this.rightKeyHeld = true
    this.state.rightPressed()
  }

  def upPressed(): Unit = {
    this.upKeyHeld = true
    this.state.upPressed()
  }

  def downPressed(): Unit = {
    this.downKeyHeld = true
    this.state.downPressed()
  }

  def leftReleased(): Unit = {
    this.leftKeyHeld = false
    this.state.leftReleased()
  }

  def rightReleased(): Unit = {
    this.rightKeyHeld = false
    this.state.rightReleased()
  }

  def upReleased(): Unit = {
    this.upKeyHeld = false
    this.state.upReleased()
  }

  def downReleased(): Unit = {
    this.downKeyHeld = false
    this.state.downReleased()
  }

  def update(dt: Double): Unit = {
    this.state.update(dt)
  }

  // End API methods


  // 5 helper methods that will be called by multiple states. Methods added here to avoid rewriting them in each state
  // where they are called

  def walkLeft(): Unit = {
    this.velocity.x = -this.walkingSpeed
  }

  def walkRight(): Unit = {
    this.velocity.x = this.walkingSpeed
  }

  def walkUp(): Unit = {
    this.velocity.y = -this.walkingSpeed
  }

  def walkDown(): Unit = {
    this.velocity.y = this.walkingSpeed
  }

  def stop(): Unit = {
    this.velocity.x = 0.0
    this.velocity.y = 0.0
  }

}
