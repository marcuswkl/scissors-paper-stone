package ch.makery.address
import ch.makery.address.model.Players
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.beans.property.StringProperty
import ch.makery.{address => cma}
import scalafx.stage.Stage
import scalafx.scene.image.{Image, ImageView}
import javafx.scene.layout.GridPane
import ch.makery.address.model.{Game, Players, Winner}
import ch.makery.address.view.{BoardGUIController, InstructionController, PlayAgainController, PlayersController, RootLayoutController, WelcomeController}
import scalafx.scene.media.{Media, MediaPlayer, MediaView}
import java.net.URL
import javax.sound.sampled._
import scala.collection.mutable.ListBuffer
import scala.reflect.io.File


object MainApp extends JFXApp {
  // transform path of RootLayout.fxml to URI for resource location.
  val rootResource = getClass.getResourceAsStream("view/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(null, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load(rootResource);
  // retrieve the root component BorderPane from the FXML

  var hasPlayAgain: Boolean = false
  var hasPlayer: Boolean = false
  var playAgainControllerOpt: Option[PlayAgainController#Controller] = None
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  var winner = new StringProperty
  var player1Name = new StringProperty
  var player2Name = new StringProperty
  var score1 = new ListBuffer[Int]
  var score2 = new ListBuffer[Int]
  var numberList = new ListBuffer[Int]
  numberList += (100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100)
  var coloredBox = new ListBuffer[Int]
  coloredBox += (1, 2, 3, 4, 5, 6, 7, 8, 9)
  var players = new Players(null, null)

  // initialize stage
  stage = new PrimaryStage {
    title = "DotsAndBoxes"
    // val audioPath = "https://www.youtube.com/watch?v=P-BqmegCUpA"
    // val audio = new Media(audioPath)
    // val mediaPlayer = new MediaPlayer(audio)
//    mediaPlayer.volume = 100
//    mediaPlayer.setAutoPlay(true)
    //val url = new URL("http://mywebpages.comcast.net/jdeshon2/wave_files/jad0001a.wav")
    //val audioIn = AudioSystem.getAudioInputStream(url)
    //val clip = AudioSystem.getClip
    //clip.open(audioIn)
    //clip.start
    scene = new Scene {
      root = roots
//      val mediaView = new MediaView(mediaPlayer)
//      root = new Group(mediaView)
    }
  }

/*  def muteMusic(): Unit ={
    mediaPlayer.volume = 0
  }*/

  def showWelcome() = {
    val resource = getClass.getResourceAsStream("view/Welcome.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)

  }

  def showInstruction() = {
    val resource = getClass.getResourceAsStream("view/Instructions.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  // actions for display board game window
  def showBoardGUI(player1 : StringProperty, player2 : StringProperty) = {
    player1Name = player1
    player2Name = player2
    hasPlayer = true
    val resource = getClass.getResourceAsStream("view/BoardGUI.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showPlayers() = {
    val resource = getClass.getResourceAsStream("view/Players.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showPlayAgain(testValue : StringProperty) = {
    winner = testValue
    val resource = getClass.getResourceAsStream("view/PlayAgain.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    playAgainControllerOpt = Option(loader.getController[PlayAgainController#Controller])
    this.roots.setCenter(roots)
    hasPlayAgain = true
  }

  // call to display WelcomePage when app start
  showWelcome()
}
