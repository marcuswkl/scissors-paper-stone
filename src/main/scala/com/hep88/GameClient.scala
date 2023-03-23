package com.hep88

import akka.actor.typed.receptionist.Receptionist
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior, PostStop}
import com.hep88.model.Player
import com.hep88.protocol.JsonSerializable
import scalafx.application.Platform
import scalafx.collections.ObservableHashSet


object GameClient {
  sealed trait Command extends JsonSerializable

  // Internal protocol
  case object start extends Command

  final case object FindTheServer extends Command

  private case class ListingResponse(listing: Receptionist.Listing) extends Command

  case class StartJoin(name: String) extends Command

  final case class Joined(list: Iterable[Player]) extends Command

  final case class StartGame() extends Command

  // Game protocol
  final case class ThrowAction(actionType: String) extends Command

  final case class EndGame(result: String) extends Command

  final case class PlayerList(list: Iterable[Player]) extends Command

  var defaultBehavior: Option[Behavior[GameClient.Command]] = None
  var remoteOpt: Option[ActorRef[GameServer.Command]] = None
  var nameOpt: Option[String] = None

  val players = new ObservableHashSet[Player]()

  def apply(): Behavior[GameClient.Command] = {
    Behaviors.setup { context =>

      // Upnp.bindPort(context)

      val listingAdapter: ActorRef[Receptionist.Listing] =
        context.messageAdapter { listing =>
          println(s"listingAdapter:listing: ${listing.toString}")
          GameClient.ListingResponse(listing)
        }

      context.system.receptionist ! Receptionist.Subscribe(GameServer.ServerKey, listingAdapter)

      // Default behaviour for game client
      defaultBehavior = Some(Behaviors.receiveMessage { message =>
        message match {
          case GameClient.start =>
            context.self ! FindTheServer
            Behaviors.same
          case FindTheServer =>
            context.system.receptionist !
              Receptionist.Find(GameServer.ServerKey, listingAdapter)
            Behaviors.same
          case ListingResponse(GameServer.ServerKey.Listing(listings)) =>
            val xs: Set[ActorRef[GameServer.Command]] = listings
            for (x <- xs) {
              remoteOpt = Some(x)
            }
            Behaviors.same
          case StartJoin(name) =>
            nameOpt = Option(name)
            remoteOpt.map(_ ! GameServer.Join(name, context.self))
            Behaviors.same
          case GameClient.Joined(x) =>
            players.clear()
            players ++= x
            Behaviors.same
          // Start the game after this command is received from the game server when there are two players in the room
          case StartGame() =>
            Platform.runLater {
              GameClientApp.showGame()
            }
            // Change to game started behaviour to receive game protocol
            gameStarted()
          case _ =>
            Behaviors.unhandled
        }
      })
      defaultBehavior.get
    }
  }

  // Game started behaviour for game client
  def gameStarted(): Behavior[GameClient.Command] = Behaviors.receive[GameClient.Command] { (context, message) =>
    message match {
      // Send the action thrown by the client to the server
      case ThrowAction(actionType) =>
        remoteOpt.get ! GameServer.Action(actionType, context.self)
        Behaviors.same
      // End the game when the result is received from the server after both players have thrown their actions
      case EndGame(result) =>
        Platform.runLater {
          GameClientApp.showResult(result)
        }
        // Change to default behaviour to receive internal protocol
        defaultBehavior.get
      case PlayerList(list: Iterable[Player]) =>
        players.clear()
        players ++= list
        Behaviors.same
    }
  }.receiveSignal {
    // Send a leave message to the server if the client terminates
    case (context, PostStop) =>
      for (name <- nameOpt;
           remote <- remoteOpt) {
        remote ! GameServer.Leave(name, context.self)
      }
      defaultBehavior.getOrElse(Behaviors.same)
  }
}
