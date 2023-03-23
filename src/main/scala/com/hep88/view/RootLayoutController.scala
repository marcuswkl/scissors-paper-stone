package com.hep88.view

import com.hep88.GameClientApp
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutController() {

  def handleHome() {
    GameClientApp.showHome()
  }

  def handleClose() {
    Platform.exit
  }

  def handleAbout(ac: ActionEvent) {
    new Alert(AlertType.Information) {
      initOwner(GameClientApp.stage)
      title = "About"
      headerText = "Scissors Paper Stone V1"
      contentText = "Copyrighted by KAMY"
    }.showAndWait()
  }

  def handleShowInstruction() {
    GameClientApp.showInstructions()
  }

}
