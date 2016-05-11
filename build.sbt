name := """fr8-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.3",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.7.3",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.7.3",
  "org.apache.httpcomponents" % "httpasyncclient" % "4.1.1",
  "org.apache.commons" % "commons-lang3" % "3.4",
  javaJdbc,
  cache,
  javaWs
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
