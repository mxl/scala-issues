name := "quill-multiple-jdbc-contexts"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:postfixOps",
    "-language:reflectiveCalls",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Xfuture",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
  ),
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots")
  )
)

val defaultDatabase = "jdbc-h2"
lazy val database = sys.props.getOrElse("database", default = defaultDatabase)

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(
    unmanagedSourceDirectories in Compile ++= Seq(
      baseDirectory.value / "src" / database / "scala"
    )
  )
  .settings(
    libraryDependencies ++= Seq(
      "io.getquill" %% "quill-jdbc" % "1.0.2-SNAPSHOT",
      "mysql"          % "mysql-connector-java" % "5.1.38",
      "com.h2database" % "h2"                   % "1.4.192"
    )
  )
