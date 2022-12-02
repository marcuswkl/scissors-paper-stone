package ch.makery.address.model
import javafx.scene.layout.{GridPane, HBox, Pane, StackPane}
import ch.makery.{address => cma}
import javafx.{scene => jfxs}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import ch.makery.address.MainApp
import ch.makery.address.MainApp.numberList
import javafx.geometry.{Pos,Insets}
import javafx.scene.layout.GridPane.clearConstraints
import scalafx.scene.Node
import scalafx.scene.text.{Font, Text}
import javafx.scene.shape.Rectangle
import javafx.scene.paint.Color
import javafx.scene.layout.HBox
import scalafx.scene.media.{Media, MediaPlayer}

import scala.collection.mutable.ListBuffer
import scalafx.scene.text.{Font, Text}

class Game3() extends Game(){

  var playerTurn = true
  var contentList = new ListBuffer[String]
  var paneList = new ListBuffer[String]
  var playGame = true
  var players = new Players(null, null)
  var currentPlayer = "playerOne"

//  val audio = new Media ("bg-music.mp3")
//  val mediaPlayer = new MediaPlayer(audio)
//  mediaPlayer.volume = 100
//  mediaPlayer.play()
//
//  def muteMusic(): Unit ={
//    mediaPlayer.volume = 0
//  }


 def showTurn1(info: GridPane): Unit = {
   val rect = new Rectangle(360, 75, Color.rgb(255, 94, 94))
   val hbox = new HBox(rect)
   hbox.setSpacing(20)
   HBox.setMargin(rect, new Insets(15, 0, 0, 210))
   val f = new Font("chalkboard", 30)
    val player1 = new Text("                     "+MainApp.player1Name.value + "'s turn!") {
      font = f
    }
   player1.setFill(Color.WHITE)
   info.add(hbox, 0, 0)
   info.add(player1, 0, 0)
  }

  def showTurn2(info: GridPane): Unit = {
    val rect = new Rectangle(360, 75, Color.rgb(135, 182, 245))
    val hbox = new HBox(rect)
    hbox.setSpacing(20)
    HBox.setMargin(rect, new Insets(15, 0, 0, 210))
    val f = new Font("chalkboard", 30)
    val player2 = new Text("                     "+MainApp.player2Name.value + "'s turn!") {
      font = f
    }
    player2.setFill(Color.WHITE)
    info.add(hbox, 0, 0)
    info.setAlignment(Pos.CENTER)
    info.add(player2,0, 0)
  }

  def showRepeat(info: GridPane): Unit = {
    val rect = new Rectangle(360, 75, Color.rgb(245, 171, 135))
    val hbox = new HBox(rect)
    hbox.setSpacing(20)
    HBox.setMargin(rect, new Insets(15, 0, 0, 210))
    rect.xProperty()
    val f = new Font("chalkboard", 28)
    val repeatedLine = new Text("                     Please click another line!") {
      font = f
    }
    repeatedLine.setFill(Color.WHITE)
    info.add(hbox, 0, 0)
    info.setAlignment(Pos.CENTER)
    info.add(repeatedLine, 0, 0)
  }



  override def addLine(pane: Pane, gridPane: GridPane, lineName: String, lineIndex: Int, paneList: ListBuffer[Pane], info:GridPane) = {

    if (playGame) {
      if (MainApp.numberList.contains(lineIndex)) {
         showRepeat(info)
      } else {

        if (playerTurn) {
          pane.setStyle("-fx-background-color: red")
          playerTurn = false
          currentPlayer = "playerOne"
        } else {
          pane.setStyle("-fx-background-color: blue")
          playerTurn = true
          currentPlayer = "playerTwo"
        }
        MainApp.numberList.update(lineIndex - 1, lineIndex)

        playerTurn = addBox(contentList, MainApp.numberList, currentPlayer, paneList: ListBuffer[Pane])

        if (playerTurn == true) {
          showTurn1(info)

        } else {
          showTurn2(info)
        }

        if (MainApp.coloredBox.size == 0) {
          playGame = false
          getWinner()
        }
        if (playGame == false) {
          MainApp.showPlayAgain(MainApp.winner)
          MainApp.numberList.clear()
          MainApp.coloredBox.clear()
          MainApp.numberList += (0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
          MainApp.coloredBox += (1, 2, 3, 4, 5, 6, 7, 8, 9)
          MainApp.score1.clear()
          MainApp.score2.clear()
        }
      }

    }
  }

  override def addBox(contentList: ListBuffer[String], numList: ListBuffer[Int], currentPlayer: String, paneList: ListBuffer[Pane]): Boolean = {
    var firstBox = false
    var secondBox = false
    var thirdBox = false
    var fourthBox = false
    var fifthBox = false
    var sixthBox = false
    var seventhBox = false
    var eighthBox = false
    var ninthBox = false
    var totalBox = new ListBuffer[Int]
    if (numList.contains(1) && numList.contains(4) && numList.contains(5) && numList.contains(8)) {
      firstBox = true
      totalBox += 1
    }
    if (numList.contains(2) && numList.contains(5) && numList.contains(6) && numList.contains(9)) {
      secondBox = true
      totalBox += 2
    }
    if (numList.contains(3) && numList.contains(6) && numList.contains(7) && numList.contains(10)) {
      thirdBox = true
      totalBox += 3
    }
    if (numList.contains(8) && numList.contains(11) && numList.contains(12) && numList.contains(15)) {
      fourthBox = true
      totalBox += 4
    }
    if (numList.contains(9) && numList.contains(12) && numList.contains(13) && numList.contains(16)) {
      fifthBox = true
      totalBox += 5
    }
    if (numList.contains(10) && numList.contains(13) && numList.contains(14) && numList.contains(17)) {
      sixthBox = true
      totalBox += 6
    }
    if (numList.contains(15) && numList.contains(18) && numList.contains(19) && numList.contains(22)) {
      seventhBox = true
      totalBox += 7
    }
    if (numList.contains(16) && numList.contains(19) && numList.contains(20) && numList.contains(23)) {
      eighthBox = true
      totalBox += 8
    }
    if (numList.contains(17) && numList.contains(20) && numList.contains(21) && numList.contains(24)) {
      ninthBox = true
      totalBox += 9
    }

    if (firstBox || secondBox || thirdBox || fourthBox || fifthBox || sixthBox || seventhBox || eighthBox || ninthBox) {
      if (firstBox && secondBox && MainApp.coloredBox.contains(1) && MainApp.coloredBox.contains(2)) {
        MainApp.coloredBox -= 1
        MainApp.coloredBox -= 2
        totalBox -= 1
        totalBox -= 2
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(0).setStyle("-fx-background-color: red")
          paneList(1).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(0).setStyle("-fx-background-color: blue")
          paneList(1).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (secondBox && thirdBox && MainApp.coloredBox.contains(2) && MainApp.coloredBox.contains(3)) {
        MainApp.coloredBox -= 2
        MainApp.coloredBox -= 3
        totalBox -= 2
        totalBox -= 3
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(1).setStyle("-fx-background-color: red")
          paneList(2).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(1).setStyle("-fx-background-color: blue")
          paneList(2).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (fourthBox && fifthBox && MainApp.coloredBox.contains(4) && MainApp.coloredBox.contains(5)) {
        MainApp.coloredBox -= 4
        MainApp.coloredBox -= 5
        totalBox -= 4
        totalBox -= 5
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(3).setStyle("-fx-background-color: red")
          paneList(4).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(3).setStyle("-fx-background-color: blue")
          paneList(4).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (fifthBox && sixthBox && MainApp.coloredBox.contains(5) && MainApp.coloredBox.contains(6)) {
        MainApp.coloredBox -= 5
        MainApp.coloredBox -= 6
        totalBox -= 5
        totalBox -= 6
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(4).setStyle("-fx-background-color: red")
          paneList(5).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(4).setStyle("-fx-background-color: blue")
          paneList(5).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (seventhBox && eighthBox && MainApp.coloredBox.contains(7) && MainApp.coloredBox.contains(8)) {
        MainApp.coloredBox -= 7
        MainApp.coloredBox -= 8
        totalBox -= 7
        totalBox -= 8
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(6).setStyle("-fx-background-color: red")
          paneList(7).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(6).setStyle("-fx-background-color: blue")
          paneList(7).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (eighthBox && ninthBox && MainApp.coloredBox.contains(8) && MainApp.coloredBox.contains(9)) {
        MainApp.coloredBox -= 8
        MainApp.coloredBox -= 9
        totalBox -= 8
        totalBox -= 9
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(7).setStyle("-fx-background-color: red")
          paneList(8).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(7).setStyle("-fx-background-color: blue")
          paneList(8).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (firstBox && fourthBox && MainApp.coloredBox.contains(1) && MainApp.coloredBox.contains(4)) {
        MainApp.coloredBox -= 1
        MainApp.coloredBox -= 4
        totalBox -= 1
        totalBox -= 4
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(0).setStyle("-fx-background-color: red")
          paneList(3).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(0).setStyle("-fx-background-color: blue")
          paneList(3).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (secondBox && fifthBox && MainApp.coloredBox.contains(2) && MainApp.coloredBox.contains(5)) {
        MainApp.coloredBox -= 2
        MainApp.coloredBox -= 5
        totalBox -= 2
        totalBox -= 5
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(1).setStyle("-fx-background-color: red")
          paneList(4).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(0).setStyle("-fx-background-color: blue")
          paneList(1).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (thirdBox && sixthBox && MainApp.coloredBox.contains(3) && MainApp.coloredBox.contains(6)) {
        MainApp.coloredBox -= 3
        MainApp.coloredBox -= 6
        totalBox -= 3
        totalBox -= 6
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(2).setStyle("-fx-background-color: red")
          paneList(5).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(2).setStyle("-fx-background-color: blue")
          paneList(5).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (fourthBox && seventhBox && MainApp.coloredBox.contains(4) && MainApp.coloredBox.contains(7)) {
        MainApp.coloredBox -= 4
        MainApp.coloredBox -= 7
        totalBox -= 4
        totalBox -= 7
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(3).setStyle("-fx-background-color: red")
          paneList(6).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(3).setStyle("-fx-background-color: blue")
          paneList(6).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (fifthBox && eighthBox && MainApp.coloredBox.contains(5) && MainApp.coloredBox.contains(8)) {
        MainApp.coloredBox -= 5
        MainApp.coloredBox -= 8
        totalBox -= 5
        totalBox -= 8
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(4).setStyle("-fx-background-color: red")
          paneList(7).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(4).setStyle("-fx-background-color: blue")
          paneList(7).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (sixthBox && ninthBox && MainApp.coloredBox.contains(6) && MainApp.coloredBox.contains(9)) {
        MainApp.coloredBox -= 6
        MainApp.coloredBox -= 9
        totalBox -= 6
        totalBox -= 9
        if (currentPlayer == "playerOne") {
          MainApp.score1 += 1
          MainApp.score1 += 1
          paneList(5).setStyle("-fx-background-color: red")
          paneList(8).setStyle("-fx-background-color: red")
          return true
        } else {
          MainApp.score2 += 1
          MainApp.score2 += 1
          paneList(5).setStyle("-fx-background-color: blue")
          paneList(8).setStyle("-fx-background-color: blue")
          return false
        }
      }
      if (firstBox && MainApp.coloredBox.contains(1)) {
          MainApp.coloredBox -= 1
          totalBox -= 1
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(0).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(0).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (secondBox && MainApp.coloredBox.contains(2)) {
          MainApp.coloredBox -= 2
          totalBox -= 2
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(1).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(1).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (thirdBox && MainApp.coloredBox.contains(3)) {
          MainApp.coloredBox -= 3
          totalBox -= 3
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(2).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(2).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (fourthBox && MainApp.coloredBox.contains(4)) {
          MainApp.coloredBox -= 4
          totalBox -= 4
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(3).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(3).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (fifthBox && MainApp.coloredBox.contains(5)) {
          MainApp.coloredBox -= 5
          totalBox -= 5
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(4).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(4).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (sixthBox && MainApp.coloredBox.contains(6)) {
          MainApp.coloredBox -= 6
          totalBox -= 6
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(5).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(5).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (seventhBox && MainApp.coloredBox.contains(7)) {
          MainApp.coloredBox -= 7
          totalBox -= 7
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(6).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(6).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (eighthBox && MainApp.coloredBox.contains(8)) {
          MainApp.coloredBox -= 8
          totalBox -= 8
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(7).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(7).setStyle("-fx-background-color: blue")
            return false
          }
        }
        if (ninthBox && MainApp.coloredBox.contains(9)) {
          MainApp.coloredBox -= 9
          totalBox -= 9
          if (currentPlayer == "playerOne") {
            MainApp.score1 += 1
            paneList(8).setStyle("-fx-background-color: red")
            return true
          } else {
            MainApp.score2 += 1
            paneList(8).setStyle("-fx-background-color: blue")
            return false
          }
        } else {
          if (currentPlayer == "playerOne") {
            return false
          } else {
            return true
          }
        }
      }
    else {
        if (currentPlayer == "playerOne") {
          return false
        }
        else {
          return true
        }
      }
  }

  override def getWinner() = {
    if (MainApp.score1.size > MainApp.score2.size) {
      MainApp.winner = MainApp.player1Name
    }
    else {
      MainApp.winner = MainApp.player2Name
    }
  }

}