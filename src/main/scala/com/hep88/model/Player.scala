package com.hep88.model

import akka.actor.typed.ActorRef
import com.hep88.GameClient
import com.hep88.protocol.JsonSerializable

case class Player(name: String, ref: ActorRef[GameClient.Command]) extends JsonSerializable {
  override def toString: String = {
    name
  }
}

