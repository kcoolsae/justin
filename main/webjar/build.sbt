name := "Justin - webjar"

normalizedName := "webjar"

// remove most of play functionality - we only need to build a webjar
scalaVersion := "2.13.15"
enablePlugins(PlayJava)
disablePlugins(PlayFilters, PlayLogback, PlayPekkoHttpServer)

// run the bootstrap build script whenever a file in scss changes
lazy val customizeBootstrap = TaskKey[Unit]("customizeBootstrap", "Calls 'sass' to customize bootstrap")

customizeBootstrap := {
    import sys.process.*
    Process ("./customize-bootstrap.sh", baseDirectory.value) !
}

Compile/compile := (Compile/compile).dependsOn(customizeBootstrap).value

// remove dependencies on scala and play in published result:
// see https://stackoverflow.com/questions/27835740/sbt-exclude-certain-dependency-only-during-publish

import scala.xml.{Elem, Comment}
import scala.xml.transform.{RewriteRule, RuleTransformer}

pomPostProcess := { (node: scala.xml.Node) =>
  new RuleTransformer(new RewriteRule {
    override def transform(node: scala.xml.Node): scala.xml.NodeSeq = node match {
      case e: Elem if e.label == "dependency"  =>
        Comment("Dependencies have been removed")
      case _ => node
    }
  }).transform(node).head
}


