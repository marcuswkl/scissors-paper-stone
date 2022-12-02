package ch.makery.address.view
import scalafxml.core.macros.sfxml
import ch.makery.address.MainApp
import scalafx.event.ActionEvent
import scalafx.stage.Stage
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

@sfxml
class InstructionController() {
  def handlePlayers(action: ActionEvent) {
    MainApp.showPlayers()
  }

  def handleStart(action: ActionEvent) {
    if(MainApp.hasPlayer){
      MainApp.showBoardGUI(MainApp.player1Name, MainApp.player2Name)
    } else{
      new Alert(AlertType.Information){
        initOwner(MainApp.stage)
        title       = "Invalid Data"
        headerText  = "No Player"
        contentText = "Please click start and enter the player name first."
      }.showAndWait()
    }
  }

  def handleExit(action :ActionEvent) {
    Platform.exit()
  }

  /*def handleMuteMusic(action: ActionEvent): Unit = {
    MainApp.muteMusic()
  }*/
}