package ch.makery.address.model
import scalafxml.core.macros.sfxml
import scalafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import ch.makery.{address => cma}
import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import scalafx.scene.Node
import scala.collection.mutable.ListBuffer

abstract class Game() {

  def addLine(pane: Pane, gridPane: GridPane, lineName: String, lineIndex: Int, paneList: ListBuffer[Pane], info:GridPane)

  def addBox(contentList: ListBuffer[String], numList: ListBuffer[Int], currentPlayer: String, paneList: ListBuffer[Pane]): Boolean

  def getWinner()
}