package com.hep88.view

import com.hep88.{GameClient, GameClientApp}
import scalafxml.core.macros.sfxml

@sfxml
class GameController() {

  def handleThrowScissors() {
    GameClientApp.userRef ! GameClient.ThrowAction("Scissors")
  }

  def handleThrowPaper() {
    GameClientApp.userRef ! GameClient.ThrowAction("Paper")
  }

  def handleThrowStone() {
    GameClientApp.userRef ! GameClient.ThrowAction("Stone")
  }

}