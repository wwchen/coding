name := "scala-coding"

version := "1.0"
organization := "com.williamchen"

scalaVersion := "2.12.2"

resolvers ++= Seq(
  "Twitter's Repository" at "https://maven.twttr.com/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.typelevel" %% "cats" % "0.9.0"
).map(_.exclude("org.slf4j", "slf4j-log4j12"))

lazy val scalaCoding = project.in(file(".")).settings()