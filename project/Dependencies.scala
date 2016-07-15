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
  val twitter4j = "org.twitter4j" % "twitter4j-core" % "4.0.4"
  val twitter4jStream = "org.twitter4j" % "twitter4j-stream" % "4.0.4"
  val codec = "commons-codec" % "commons-codec" % "1.10"
  val commonio = "commons-io" % "commons-io" % "2.5"
  val httpclient = "org.apache.httpcomponents" % "httpclient" % "4.5.2"
  val httpcore = "org.apache.httpcomponents" % "httpcore" % "4.4.5"
  val signpost = "oauth.signpost" % "signpost-core" % "1.2"
  val signcommon = "oauth.signpost" % "signpost-commonshttp4" % "1.2"
  val logback = "ch.qos.logback" %  "logback-classic" % "1.1.7"
  val config= "com.typesafe" % "config" % "1.3.0"
}
