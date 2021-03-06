name := "scala-coding"
scalaVersion := "2.12.2"

version := "1.0"
organization := "com.williamchen"

mainClass in(Compile, run) := Some("apps.MainApp")

resolvers ++= Seq(
  "Twitter's Repository" at "https://maven.twttr.com/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.typelevel" %% "cats-core" % "1.0.0-MF",
  "io.chymyst" %% "chymyst-core" % "latest.integration",
  "com.twitter" %% "util-collection" % "7.0.0",
  "org.typelevel" %% "cats-core" % "1.0.0-MF",
  "commons-io" % "commons-io" % "2.6"
).map(_.exclude("org.slf4j", "slf4j-log4j12"))

lazy val scalaCoding = project.in(file(".")).settings()

addCommandAlias("dining", "runMain apps.DiningPhilosophers")
addCommandAlias("lyft", "runMain lyft.VersionedKeyValueStoreRunner")