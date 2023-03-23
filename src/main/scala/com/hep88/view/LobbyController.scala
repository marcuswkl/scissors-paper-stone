package com.hep88.view

import akka.actor.typed.ActorRef
import com.hep88.GameClient
import scalafx.scene.control.TextField
import scalafxml.core.macros.sfxml

@sfxml
class LobbyController(private val nameField: TextField) {

  var gameClientRef: Option[ActorRef[GameClient.Command]] = None

  def handleJoin(): Unit = {
    if (nameField != null) {
      gameClientRef map (_ ! GameClient.StartJoin(nameField.text()))
    }
  }

}