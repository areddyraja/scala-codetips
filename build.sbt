lazy val root = ( project in file(".") ).
  settings(
    name := "scalacode-codetips",
    version := "0.0.0.1-SNAPSHOT",
    scalaVersion := "2.11.7"
  ) 

// The sequence of library dependencies.
libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "0.8.2.0" withSources(),
  "com.typesafe.akka" %% "akka-remote" % "2.3.7" withSources(),
  "org.xerial.snappy" % "snappy-java" % "1.1.1.6" withSources(),
  "io.spray" %%  "spray-json" % "1.3.2" withSources(),
  "joda-time" % "joda-time" % "2.5" withSources(),
  "org.slf4j" % "slf4j-api" % "1.7.7" withSources(),
  "ch.qos.logback" % "logback-core" % "1.1.2" withSources(),
  "net.jpountz.lz4" % "lz4" % "1.3.0" withSources()
)

scalacOptions ++= Seq( 
    "-feature", 
    "-language:implicitConversions", 
    "-language:higherKinds", 
    "-language:existentials", 
    "-language:postfixOps"
)

test in assembly := {}
