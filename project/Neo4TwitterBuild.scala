import play.sbt.PlayScala
import sbt.Keys._
import sbt._

object Neo4TwitterBuild extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val neo4twitter = Project(id = "neo4twitter", base = file(".")) aggregate(neo4stream)

  lazy val neo4stream = Project("neo4stream", file("neo4stream"))
    .settings(basicSettings ++ assemblySetting)
    .settings(libraryDependencies ++= compile(neo4j, twitter)
       ++ test(scalatest))
}
