package com.hep88

import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.adapter._
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.cluster.typed._
import akka.management.cluster.bootstrap.ClusterBootstrap
import akka.management.scaladsl.AkkaManagement
import com.hep88.model.Player
import com.hep88.protocol.JsonSerializable
import com.typesafe.config.ConfigFactory
import scalafx.collections.ObservableHashSet

import scala.collection.mutable.ListBuffer

object GameServer {
  // Lobby protocols
  sealed trait Command extends JsonSerializable

  case class Join(name: String, from: ActorRef[GameClient.Command]) extends Command

  case class Leave(name: String, from: ActorRef[GameClient.Command]) extends Command

  // Game protocols
  case class Action(actionType: String, from: ActorRef[GameClient.Command]) extends Command

  // State values
  val ServerKey: ServiceKey[GameServer.Command] = ServiceKey("GameServer")
  val players = new ObservableHashSet[Player]()
  val actions = new ListBuffer[Action]()
  var gameIsRunning = false
  val playerNames = new ListBuffer[String]()
  var result = new String

  val defaultBehaviour: Option[Behavior[GameServer.Command]] = None

  players.onChange { (ns, _) =>
    for (player <- ns) {
      player.ref ! GameClient.PlayerList(players.toList)
    }
    println(players)
  }

  def apply(): Behavior[GameServer.Command] = Behaviors.setup { context =>

    context.system.receptionist ! Receptionist.Register(ServerKey, context.self)

    //Upnp.bindPort(context)

    // Default behaviour for game server
    val defaultBehaviour = Option(Behaviors.receiveMessage[com.hep88.GameServer.Command] { message =>
      message match {
        case Join(name, from) =>
          players += Player(name, from)
          from ! GameClient.Joined(players.toList)
          // If the room has two players, start the game
          if (players.size == 2) {
            for (player <- players) {
              player.ref ! GameClient.StartGame()
              gameIsRunning = true
            }
          }
          Behaviors.same
        case Leave(name, from) =>
          players -= Player(name, from)
          // If a player disconnects or leaves during an ongoing game, the other player automatically wins
          if (gameIsRunning) {
            result = players.head.toString + " wins!"
          }
          players.head.ref ! GameClient.EndGame(result)
          Behaviors.same
        // Defines the type of action (scissors, paper or stone) and the actor reference who performed the action
        case Action(actionType, from) =>
          actions += Action(actionType, from)
          // Once the server receives two actions, the result is computed
          if (actions.size == 2) {
            // Match action actor reference to player actor reference to obtain player name
            for (action <- actions) {
              for (player <- players) {
                if (player.ref == action.from) {
                  playerNames += player.name
                }
              }
            }
            // Produces the result based on the actions from both players
            if (actions(0).actionType == "Scissors" && actions(1).actionType == "Paper") {
              result = playerNames(0) + " wins!"
            } else if (actions(0).actionType == "Scissors" && actions(1).actionType == "Stone") {
              result = playerNames(1) + " wins!"
            } else if (actions(0).actionType == "Paper" && actions(1).actionType == "Scissors") {
              result = playerNames(1) + " wins!"
            } else if (actions(0).actionType == "Paper" && actions(1).actionType == "Stone") {
              result = playerNames(0) + " wins!"
            } else if (actions(0).actionType == "Stone" && actions(1).actionType == "Scissors") {
              result = playerNames(0) + " wins!"
            } else if (actions(0).actionType == "Stone" && actions(1).actionType == "Paper") {
              result = playerNames(1) + " wins!"
            } else if (actions(0).actionType == actions(1).actionType) {
              result = "Its a draw!"
            }
            // End the game and send the result to all players
            for (player <- players) {
              player.ref ! GameClient.EndGame(result)
            }
          }
          Behaviors.same
        case _ =>
          Behaviors.unhandled
      }
    })
    defaultBehaviour.get
  }
}

object Server extends App {
  // Server setup
  val config = ConfigFactory.load()
  val mainSystem = akka.actor.ActorSystem("HelloSystem", MyConfiguration.askDevConfig().withFallback(config)) //classic
  val typedSystem: ActorSystem[Nothing] = mainSystem.toTyped
  val cluster = Cluster(typedSystem)
  cluster.manager ! Join(cluster.selfMember.address)
  AkkaManagement(mainSystem).start()
  // val serviceDiscovery = Discovery(mainSystem).discovery
  ClusterBootstrap(mainSystem).start()
  // val greeterMain: ActorSystem[GameServer.Command] = ActorSystem(GameServer(), "HelloSystem")
  mainSystem.spawn(GameServer(), "GameServer")
}
