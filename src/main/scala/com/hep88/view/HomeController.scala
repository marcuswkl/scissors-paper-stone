package com.hep88.view

import com.hep88.GameClientApp
import scalafxml.core.macros.sfxml

@sfxml
class HomeController {
  def handleShowLobby() {
    GameClientApp.showLobby()
  }

  def handleShowInstruction() {
    GameClientApp.showInstructions()
  }

}