name := "Justin - parent project"

scalaVersion := "2.13.15"

// subprojects
//////////////

lazy val root = (project in file(".")).aggregate(
  db, webapp, webjar
).settings(
  publish / skip := true
)

lazy val db = project in file("db")

lazy val webjar = project in file("webjar")

lazy val webapp = (project in file("webapp"))
  .dependsOn(db, webjar)
  .enablePlugins(PlayJava)

// settings common to all projects
//////////////////////////////////

Global / logLevel := Level.Warn

ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / organization := "be.ugent.justin"
ThisBuild / crossPaths := false
ThisBuild / autoScalaLibrary := false
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / scalacOptions += "-release:21"
ThisBuild / javacOptions ++= Seq("-source", "21", "-target", "21")

// no documentation or sources in packaged jars
ThisBuild / packageDoc / publishArtifact := false
ThisBuild / packageSrc / publishArtifact := false
