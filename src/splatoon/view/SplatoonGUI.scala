package splatoon.view

import javafx.scene.input.KeyEvent
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.{Group, Scene}
import splatoon.controller.WASDInputs
import splatoon.model.Game

object SplatoonGUI extends JFXApp{

  var lastUpdateTime: Long = System.nanoTime()

  val game = new Game()

  val scaleFactor: Double = 30.0

  val windowWidth: Double = game.gridWidth * scaleFactor
  val windowHeight: Double = game.gridHeight * scaleFactor

  val playerSpriteSize: Double = game.playerSize

  var sceneGraphics: Group = new Group()

  val playerSprite: Shape = playerSprite(game.player.location.x, game.player.location.y, Color.Blue)

  sceneGraphics.children.add(playerSprite)

  //   Convert game coordinated to GUI coordinates
  //   We would use adapter if the GUI was using an interface for coordinates. Since the GUI wants us to set x and y
  //   manually writing methods will suffice
  def convertX(gameX: Double, width: Double): Double = {
    (gameX - width / 2.0)*scaleFactor
  }

  def convertY(gameY: Double, height: Double): Double = {
    (gameY - height / 2.0)*scaleFactor
  }

  def playerSprite(xLocation: Double, yLocation: Double, color: Color): Shape = {
    new Rectangle {
      width =  30
      height = 30
      translateX = convertX(xLocation, playerSpriteSize)
      translateY = convertY(yLocation, playerSpriteSize)
      fill = color
    }
  }

  this.stage = new PrimaryStage {
    this.title = "Splatoon"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)

      addEventHandler(KeyEvent.ANY, new WASDInputs(game.player))
    }

    val update: Long => Unit = (time: Long) => {
      val dt: Double = (time - lastUpdateTime) / 1000000000.0
      lastUpdateTime = time
      game.update(dt)

      playerSprite.translateX.value = convertX(game.player.location.x, playerSpriteSize)
      playerSprite.translateY.value = convertY(game.player.location.y, playerSpriteSize)

    }

    // Start Animations. Calls update 60 times per second (takes update as an argument)
    AnimationTimer(update).start()
  }


}
