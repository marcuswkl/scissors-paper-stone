package ch.makery.address.view

import scalafxml.core.macros.sfxml
import ch.makery.address.MainApp
import scalafx.scene.control.{TextField, TableColumn, Alert}
import scalafx.event.ActionEvent
import scalafx.stage.Stage
import scalafx.Includes._
import ch.makery.address.model.Players
import javafx.scene.control.Label;

@sfxml
class PlayersController(
                         private val name1Field : TextField,
                         private val name2Field : TextField
                       ) {
  var startPlay = false
  var players = new Players(null, null)

  def handleStart(action: ActionEvent) {
    if(players.isInputValid(name1Field.text.value, name2Field.text.value)){
      startPlay = true
      players.name1 = name1Field.text
      players.name2 = name2Field.text
      MainApp.showBoardGUI(players.name1, players.name2)
    } else {
      MainApp.showPlayers()
    }
  }

 /* def handleMuteMusic(action: ActionEvent): Unit = {
    MainApp.muteMusic()
  }*/
}