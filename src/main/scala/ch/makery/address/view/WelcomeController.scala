package ch.makery.address.view

import scalafxml.core.macros.sfxml
import ch.makery.address.MainApp
import scalafx.scene.control.{TextField, TableColumn, Label, Alert}
import scalafx.event.ActionEvent
import scalafx.stage.Stage
import scalafx.Includes._

@sfxml
class WelcomeController {
  def handlePlayers(action: ActionEvent) {
    MainApp.showPlayers()
  }

  def handleShowInstruction(action: ActionEvent) {
    MainApp.showInstruction()
  }

  /*def handleMuteMusic(action: ActionEvent): Unit ={
    MainApp.muteMusic()
  }*/
}