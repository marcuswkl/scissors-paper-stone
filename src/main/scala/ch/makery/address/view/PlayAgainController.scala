package ch.makery.address.view

import scalafxml.core.macros.sfxml
import ch.makery.address.MainApp
import scalafx.stage.Stage
import scalafx.Includes._
import scalafx.event.ActionEvent
import scala.collection.mutable.ListBuffer
import javafx.scene.control.Label;
import scalafx.application.Platform
import scalafx.scene.shape.Rectangle
import javafx.scene.layout.GridPane
import scalafx.scene.text.{Font, Text}
import ch.makery.address.MainApp
import ch.makery.address.model.{Game, Game3, Players, Winner}

@sfxml
class PlayAgainController(
                           private val gridPane: GridPane,
                           private val resultBox: Rectangle
                         ) {
  var dialogStage : Stage = null
  var ticTacToe3 = new Game3()
  var contentList = new ListBuffer[String]
  var numList = new ListBuffer[Int]
  var players = new Players(null, null)
  var winner = new Winner()

  def handleShowResult(action :ActionEvent) {
    winner.printWinner(gridPane)
  }

  def handleStart() {
    MainApp.showBoardGUI(MainApp.player1Name, MainApp.player2Name)
    MainApp.numberList.clear()
    MainApp.coloredBox.clear()
    MainApp.numberList += (100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100)
    MainApp.coloredBox += (1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  def handleExit(action :ActionEvent) {
    Platform.exit()
  }

  /*def handleMuteMusic(action: ActionEvent): Unit = {
    MainApp.muteMusic()
  }*/
}