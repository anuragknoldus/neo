name := "neowith_facebookdata"

version := "1.0"

val neo = "org.neo4j" % "neo4j-kernel" % "3.1.0-M04"
val facebook = "com.facebook.api" % "facebook-util" % "1.8.1"

lazy val commonSettings = Seq(
  organization := "com.knoldus.neo",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "neowith_facebookdata",
    libraryDependencies ++= Seq(neo,facebook)
)
