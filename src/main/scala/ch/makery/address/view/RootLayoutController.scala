package ch.makery.address.view
import scalafxml.core.macros.sfxml
import scalafx.application.Platform
import ch.makery.address.MainApp
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

@sfxml
class RootLayoutController() {
  def handleClose(){
    Platform.exit
  }

  def handleHome() {
    MainApp.showWelcome()
  }

  def handleNewGame3(){
    MainApp.numberList.clear()
    MainApp.coloredBox.clear()
    MainApp.numberList += (100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100)
    MainApp.coloredBox += (1, 2, 3, 4, 5, 6, 7, 8, 9)
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

  def handleNewPlayer(){
    MainApp.showPlayers()
  }

  def handleAbout(ac: ActionEvent) {
    new Alert(AlertType.Information){
      initOwner(MainApp.stage)
      title       = "About"
      headerText  = "Dots and Boxes V0.1"
      contentText = "Copyrighted by KAMY"
    }.showAndWait()
  }

  def handleShowInstruction() {
    MainApp.showInstruction()
  }


}
