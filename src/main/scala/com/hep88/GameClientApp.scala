package com.hep88

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.adapter._
import akka.cluster.typed._
import akka.discovery.ServiceDiscovery.Resolved
import akka.discovery.{Discovery, Lookup, ServiceDiscovery}
import com.typesafe.config.ConfigFactory
import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

import scala.concurrent.Future
import scala.concurrent.duration._


object GameClientApp extends JFXApp {
  // Game client app setup
  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
  val config = ConfigFactory.load()
  val mainSystem = akka.actor.ActorSystem("HelloSystem", MyConfiguration.askDevConfig().withFallback(config))
  val greeterMain: ActorSystem[Nothing] = mainSystem.toTyped

  val cluster = Cluster(greeterMain)

  val discovery: ServiceDiscovery = Discovery(mainSystem).discovery

  val userRef = mainSystem.spawn(GameClient(), "GameClient")

  def joinPublicSeedNode(): Unit = {
    val lookup: Future[Resolved] =
      discovery.lookup(Lookup("wm.hep88.com").withPortName("hellosystem").withProtocol("tcp"), 1.second)

    lookup.foreach(x => {
      val result = x.addresses
      result map { x =>
        val address = akka.actor.Address("akka", "HelloSystem", x.host, x.port.get)
        cluster.manager ! JoinSeedNodes(List(address))
      }
    })
  }

  def joinLocalSeedNode(): Unit = {
    val address = akka.actor.Address("akka", "HelloSystem", MyConfiguration.localAddress.get.getHostAddress, 20000)
    cluster.manager ! JoinSeedNodes(List(address))
  }

  joinLocalSeedNode()

  // Game client app GUI setup

  // transform path of RootLayout.fxml to URI for resource location.
  val rootResource = getClass.getResourceAsStream("view/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(null, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load(rootResource)
  // retrieve the root component BorderPane from the FXML
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  // initialize stage
  stage = new PrimaryStage {
    title = "ScissorsPaperStone"
    scene = new Scene {
      root = roots
    }
  }

  // Change GUI page methods

  def showHome() = {
    val resource = getClass.getResourceAsStream("view/Home.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)

  }

  def showInstructions() = {
    val resource = getClass.getResourceAsStream("view/Instructions.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showLobby() = {
    val resource = getClass.getResourceAsStream("view/Lobby.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
    val lobbyController = loader.getController[com.hep88.view.LobbyController#Controller]()
    lobbyController.gameClientRef = Option(userRef)
  }

  def showGame() = {
    val resource = getClass.getResourceAsStream("view/Game.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showResult(result: String) = {
    val resource = getClass.getResourceAsStream("view/Result.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
    val resultController = loader.getController[com.hep88.view.ResultController#Controller]()
    resultController.result = result
  }

  // Show home page when app starts
  showHome()

  stage.onCloseRequest = handle({
    mainSystem.terminate
  })

}