name := "Justin - webapp"

normalizedName := "webapp"

scalaVersion:= "2.13.15"

libraryDependencies ++= Seq(
  javaCore, guice, javaJdbc,

  "be.ugent.caagt" %% "play-utils" % "1.1",
  "org.webjars" % "font-awesome" % "6.6.0",
  "org.playframework" %% "play-mailer" % "10.1.0",
  "org.playframework" %% "play-mailer-guice" % "10.1.0",

  "org.projectlombok" % "lombok" % "1.18.36" % Compile,

  "org.postgresql" % "postgresql" % "42.7.4" % Runtime
)

// Include local template extensions
// TODO are both of them needed?
TwirlKeys.templateImports ++= Seq(
  "views.html.ext._",
  "views.html.b5._"
)

// Include template extensions for be.ugent.caagt:play-utils
TwirlKeys.templateImports ++= Seq(
  "_root_.be.ugent.caagt.play.util.TemplateJavaMagic._",
  "_root_.common._",
  "views.html.be.ugent.caagt.play.ext._"
)

// Add imports to play util binders for routes
play.sbt.routes.RoutesKeys.routesImport ++= Seq(
  "_root_.be.ugent.caagt.play.binders._"
)

// do not generate javadoc
Compile / doc / sources := Seq.empty
Compile / doc / scalacOptions += "-no-java-comments"

// during development runs on port 9001
PlayKeys.devSettings += "play.server.http.port" -> "9001"
