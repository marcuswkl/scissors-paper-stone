package ch.makery.address.model
import ch.makery.address.MainApp
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}


class Winner() {
  def printWinner(gridPane : GridPane) {
    val f = new Font("chalkboard", 30)
    val name1 = new Text("             " + MainApp.player1Name.apply() + " is the Winner!") {
      font = f
    }
    val name2 = new Text("             " + MainApp.player2Name.apply() + " is the Winner!") {
      font = f
    }
    name1.setFill(Color.rgb(56, 23, 4))
    name2.setFill(Color.rgb(56, 23, 4))

    if(MainApp.winner.value == MainApp.player1Name.value){
      gridPane.add(name1, 1, 1)
    } else if(MainApp.winner.value == MainApp.player2Name.value){
      gridPane.add(name2, 1, 1)
    }
  }
}