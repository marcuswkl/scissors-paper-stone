package com.hep88.view

import com.hep88.GameClientApp
import scalafxml.core.macros.sfxml

@sfxml
class InstructionsController() {

  def handleHome() {
    GameClientApp.showHome()
  }

}