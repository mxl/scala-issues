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

val quillVersion = "1.0.2-SNAPSHOT"
val jdbcH2 = "jdbc-h2"
val asyncPostgres = "async-postgres"
val defaultDatabase = jdbcH2
val database = sys.props.getOrElse("database", default = defaultDatabase)
val contextDependencies = Map(
  jdbcH2 -> Seq(
    "io.getquill" %% "quill-jdbc" % quillVersion,
    "com.h2database" % "h2" % "1.4.192"
  ),
  asyncPostgres -> Seq(
    "io.getquill" %% "quill-async-postgres" % quillVersion
  )
)

lazy val contextModule = project.in(file(database))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= contextDependencies(database)
  )

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .dependsOn(contextModule)
