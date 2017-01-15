import sbt._
import sbt.Keys._

object QuillTestBuild extends Build {
  lazy val quillTest = Project(
    id = "quill-test",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "Quill Test",
      organization := "com.examples",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.11.8",
      libraryDependencies ++= Seq(
        "com.h2database" % "h2" % "1.4.192",
        "io.getquill" %% "quill-jdbc" % "1.0.1"
      )
    )
  )
}