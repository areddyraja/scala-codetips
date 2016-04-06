lazy val root = ( project in file(".") ).
  settings(
    name := "scalacode-codetips",
    version := "0.0.0.1-SNAPSHOT",
    scalaVersion := "2.11.7"
  ) 

// The sequence of library dependencies.
libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.0" withSources(),
  "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.0" withSources(),
  "io.spray" % "spray-json_2.11" % "1.3.2" withSources(),
  "io.spray" % "spray-can_2.11" % "1.3.3" withSources(),
  "io.spray" % "spray-routing_2.11" % "1.3.3" withSources(),
  "joda-time" % "joda-time" % "2.8.2" withSources(),
  "org.slf4j" % "slf4j-api" % "1.7.12" withSources(),
  "javax.mail" % "mail" % "1.4.7" withSources(),
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.2" withSources(),
  "com.valchkou.datastax" % "cassandra-driver-mapping" % "2.1.2" withSources(),
  "org.xerial.snappy" % "snappy-java" % "1.1.1.6" withSources(),
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13" withSources(),
  "ch.qos.logback" % "logback-core" % "1.1.3" withSources(),
  "ch.qos.logback" % "logback-classic" % "1.1.3" withSources(),
  "com.eaio.uuid" % "uuid" % "3.2" withSources(),
  "org.apache.httpcomponents" % "httpclient" % "4.5.1" withSources(),
  "org.scalatest" % "scalatest_2.11" % "3.0.0-M7" withSources(),
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
