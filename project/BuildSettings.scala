import sbt.Keys._
import sbt._
import sbtassembly.Plugin.AssemblyKeys._
import sbtassembly.Plugin.{MergeStrategy, PathList, _}

object BuildSettings {
  val VERSION = "1.1-SNAPSHOT"

  lazy val basicSettings = Seq(
    version := "1.0-SNAPSHOT",
    homepage := Some(new URL("http://neo4twitter.com")),
    organization := "neo4twitter",
    organizationHomepage := Some(new URL("http://neo4twitter.com")),
    description := "show twitter data in graph",
    startYear := Some(2016),
    scalaVersion := "2.11.8",
    parallelExecution in ThisBuild := false,
    parallelExecution in Test := false,
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
    resolvers ++= Dependencies.resolutionRepos,
    updateOptions := updateOptions.value.withCachedResolution(true),
    scalacOptions := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-Xlog-reflective-calls"
    )
  )


  val meta = """META.INF(.)*""".r
  val assemblySetting = assemblySettings ++ Seq(
    jarName in assembly := "neo4twitter.jar",
    test in assembly := {},
    mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => {
      case PathList("javax", "servlet", xs@_*) => MergeStrategy.last
      case PathList("javax", "activation", xs@_*) => MergeStrategy.last
      case PathList("org", "apache", xs@_*) => MergeStrategy.last
      case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.last
      case PathList("org", "datanucleus", xs@_*) => MergeStrategy.last
      case PathList("plugin.properties") => MergeStrategy.last
      case PathList("plugin.xml") => MergeStrategy.rename
      case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
      case meta(_) => MergeStrategy.discard
      case x => old(x)
    }
    },
    run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run)),
    excludedJars in assembly <<= (fullClasspath in assembly) map {
      _ filter { cp =>
        List("servlet-api", "guice-all", "junit", "uuid",
          "jetty", "jsp-api-2.0", "antlr", "avro", "slf4j-log4j", "log4j-1.2",
          "scala-actors", "commons-cli", "stax-api", "mockito").exists(cp.data.getName.startsWith(_))
      }
    }

  )

}
