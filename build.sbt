import sbt.project

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.12"

lazy val root = (project in file("."))
  .settings(
    name := "DotsAndBoxes"
  )

// https://mvnrepository.com/artifact/org.scalafx/scalafx
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.192-R14"
//libraryDependencies += "org.scalafx" %% "scalafx" % "12.0.2-R18"

// https://mvnrepository.com/artifact/org.scalafx/scalafxml-core-sfx8
libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.5"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

fork := true
