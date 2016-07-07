import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
    "Atlassian Releases" at "https://maven.atlassian.com/public/",
    "Scalaz Bintray" at "https://dl.bintray.com/scalaz/releases"
  )

  def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  def runtime(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")

  def container(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  
  val neo4j = "org.neo4j" % "neo4j-kernel" % "3.1.0-M04"
  val scalatest = "org.scalatest" %% "scalatest" % "2.2.6"
  val twitter = "com.twitter" % "util-core_2.10" % "6.34.0"
}
