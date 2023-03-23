package com.hep88.view

import com.hep88.GameClientApp
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import scalafx.application.Platform
import scalafx.scene.text.{Font, Text}
import scalafxml.core.macros.sfxml


@sfxml
class ResultController(private val gridPane: GridPane) {

  var result: String = new String

  def handleShowResult() {
    val f = new Font("chalkboard", 30)
    val resultText = new Text(result) {
      font = f
    }
    resultText.setFill(Color.rgb(56, 23, 4))
    gridPane.add(resultText, 1, 1)
  }

  def handleShowHome() {
    GameClientApp.showHome()
  }

  def handleExit() {
    Platform.exit()
  }

}