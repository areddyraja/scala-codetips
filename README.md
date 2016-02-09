# scala-codetips

### SBT 0.13.7
Simple Build Tool can be downloaded from [SBT Downloads]. Apart from SBT, there are two plugins which would be needed. [SBT Eclipse] for generating project configuration for Eclipse IDE. [SBT Assembly] for packaging everything nicely to a single power pack JAR.
<br>
For these plugins to work, please add the following to <code>~/.sbt/0.13/plugins/plugins.sbt</code>:
```sh
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.5.0")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.12.0")
```
Please make sure SBT is in path.
