package ch.makery.address.model
import javafx.scene.layout.GridPane
import ch.makery.{address => cma}
import javafx.{scene => jfxs}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import scalafx.beans.property.{StringProperty, IntegerProperty}
import scalafx.scene.control.Alert
import scalafx.stage.Stage
import scalafx.Includes._


class Players(_name1: String, _name2: String) {
  var name1 = new StringProperty(_name1)
  var name2 = new StringProperty(_name2)
  var dialogStage : Stage = null
  var winner = ""

  def nullChecking (x : String) = x == null || x.length == 0

  def isInputValid(name1 : String, name2 : String) : Boolean = {
    var errorMessage = ""

    if(nullChecking(name1))
      errorMessage += "No valid player 1's name!\n"
    if(nullChecking(name2))
      errorMessage += "No valid player 2's name!\n"
    if(name1 == name2)
      errorMessage += "Please enter different names!\n"
    if (errorMessage.length() == 0) {
      return true;
    } else {
      val alert = new Alert(Alert.AlertType.Error) {
        initOwner(dialogStage)
        title = "Invalid Fields"
        headerText = "Please correct invalid fields"
        contentText = errorMessage
      }.showAndWait()
      return false
    }
  }
}

