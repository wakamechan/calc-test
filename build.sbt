import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies ++= Seq(
    scalaTest % Test,
    "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"
    )
  )
