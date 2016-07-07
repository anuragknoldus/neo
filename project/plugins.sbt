addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.0")

// SBT Scalastyle Plugin
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.1.0")


resolvers += "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += Resolver.file("dbs", file("/home/knoldus/.ivy2/local/knoldus")) transactional()
resolvers += Classpaths.sbtPluginReleases
