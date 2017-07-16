name := "scala-template"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq(
    "-target:jvm-1.8"
    , "-feature"
    , "-deprecation"
    , "-Xfatal-warnings"
    , "-Xmax-classfile-name", "100"
    , "-unchecked"
    , "-language:implicitConversions"
    , "-language:reflectiveCalls"
    , "-language:postfixOps"
    , "-language:higherKinds"
    , "-encoding", "UTF-8"
    , "-Yno-adapted-args"
    , "-Xlint"
    , "-Ywarn-numeric-widen"
    , "-Ywarn-value-discard"
    , "-Xfuture"
    , "-Xlog-implicits"
  ),
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint"),
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val macrosModule = project.in(file("macro"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect",
      "org.scala-lang" % "scala-compiler"
    ).map(_  % scalaVersion.value)
  )

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "io.getquill" %% "quill-core" % "1.3.0"
    )
  )
  .dependsOn(macrosModule)