package ch.makery.address.view
import javafx.scene.layout.StackPane
import scalafx.scene.control
import scalafx.beans.property.StringProperty
import scalafx.event.ActionEvent
//import scalafx.scene.layout.Pane
import scalafx.scene.Scene
import scalafxml.core.macros.sfxml
import javafx.scene.layout.{GridPane, Pane}
import ch.makery.address.MainApp
import ch.makery.address.model.{Game, Game3, Players, Winner}
import scalafx.scene.text.{Font, Text}
import javafx.scene.shape.Rectangle
import javafx.scene.paint.Color
import javafx.scene.layout.{GridPane, HBox, Pane, StackPane}
import javafx.geometry.{Pos,Insets}

import scala.collection.mutable.ListBuffer

@sfxml
class BoardGUIController(
                          private val gridPane: GridPane,
                          private val oneZero : Pane,
                       private val oneTwo: Pane,
                       private val oneFour: Pane,
                       private val oneSix: Pane,
                       private val threeZero: Pane,
                       private val threeTwo: Pane,
                       private val threeFour: Pane,
                       private val threeSix: Pane,
                       private val fiveZero: Pane,
                       private val fiveTwo: Pane,
                       private val fiveFour: Pane,
                       private val fiveSix: Pane,
                       private val zeroOne: Pane,
                       private val zeroThree: Pane,
                       private val zeroFive: Pane,
                       private val twoOne: Pane,
                       private val twoThree: Pane,
                       private val twoFive: Pane,
                       private val fourOne: Pane,
                       private val fourThree: Pane,
                       private val fourFive: Pane,
                       private val sixOne: Pane,
                       private val sixThree: Pane,
                       private val sixFive: Pane,
                          private val first: Pane,
                          private val second: Pane,
                          private val third: Pane,
                          private val fourth: Pane,
                          private val fifth: Pane,
                          private val sixth: Pane,
                          private val seventh: Pane,
                          private val eighth: Pane,
                          private val ninth: Pane,
                        private val info:GridPane
                     ) {
  var game = new Game3()
  var paneList = new ListBuffer[Pane]
  paneList += (first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)


  def showTurn1(info: GridPane): Unit = {
    val rect = new Rectangle(360, 75, Color.rgb(255, 94, 94))
    val hbox = new HBox(rect)
    hbox.setSpacing(30)
    HBox.setMargin(rect, new Insets(15, 0, 0, 210))
    val f = new Font("chalkboard", 30)
    val player1 = new Text("                     " + MainApp.player1Name.value + "'s turn!") {
      font = f
    }
    player1.setFill(Color.WHITE)
    info.add(hbox, 0, 0)
    info.add(player1, 0, 0)
  }

  if (MainApp.numberList.count(_ == 100) == 33) {
    showTurn1(info)
  }


  def clickOneZero() = {
    game.addLine(oneZero, gridPane, "oneZero", 1, paneList, info)
  }

  def clickThreeZero() = {
    game.addLine(threeZero, gridPane, "threeZero", 2, paneList, info)
  }

  def clickFiveZero() = {
    game.addLine(fiveZero, gridPane, "fiveZero", 3, paneList, info)
  }
  def clickZeroOne() = {
    game.addLine(zeroOne, gridPane, "zeroOne", 4, paneList, info)
  }

  def clickTwoOne() = {
    game.addLine(twoOne, gridPane, "twoOne", 5, paneList, info)
  }

  def clickFourOne() = {
    game.addLine(fourOne, gridPane, "fourOne", 6, paneList, info)
  }

  def clickSixOne() = {
    game.addLine(sixOne, gridPane, "sixOne", 7, paneList, info)
  }

  def clickOneTwo() = {
    game.addLine(oneTwo, gridPane, "oneTwo", 8, paneList, info)
  }

  def clickThreeTwo() = {
    game.addLine(threeTwo, gridPane, "threeTwo", 9, paneList, info)
  }

  def clickFiveTwo() = {
    game.addLine(fiveTwo, gridPane, "fiveTwo", 10, paneList, info)
  }

  def clickZeroThree() = {
    game.addLine(zeroThree, gridPane, "zeroThree", 11, paneList, info)
  }

  def clickTwoThree() = {
    game.addLine(twoThree, gridPane, "twoThree", 12, paneList, info)
  }

  def clickFourThree() = {
    game.addLine(fourThree, gridPane, "fourThree", 13, paneList, info)
  }

  def clickSixThree() = {
    game.addLine(sixThree, gridPane, "sixThree", 14, paneList, info)
  }

  def clickOneFour() = {
    game.addLine(oneFour, gridPane, "oneFour", 15, paneList, info)
  }

  def clickThreeFour() = {
    game.addLine(threeFour, gridPane, "threeFour", 16, paneList, info)
  }

  def clickFiveFour() = {
    game.addLine(fiveFour, gridPane, "fiveFour", 17, paneList, info)
  }

  def clickZeroFive() = {
    game.addLine(zeroFive, gridPane, "zeroFive", 18, paneList, info)
  }

  def clickTwoFive() = {
    game.addLine(twoFive, gridPane, "twoFive", 19, paneList, info)
  }

  def clickFourFive() = {
    game.addLine(fourFive, gridPane, "fourFive", 20, paneList, info)
  }

  def clickSixFive() = {
    game.addLine(sixFive, gridPane, "sixFive", 21, paneList, info)
  }

  def clickOneSix() = {
    game.addLine(oneSix, gridPane, "oneSix", 22, paneList, info)
  }

  def clickThreeSix() = {
    game.addLine(threeSix, gridPane, "threeSix", 23, paneList, info)
  }

  def clickFiveSix() = {
    game.addLine(fiveSix, gridPane, "fiveSix", 24, paneList, info)
  }


  /*def handleMuteMusic(action: ActionEvent): Unit = {
    MainApp.muteMusic()
  }*/

}