package objects

import java.io.File
/**
 * @author reddyraja
 */
object CollectorConfig extends Configuration {
  def configFile: File = new File(sys.props("user.dir") + java.io.File.separator + "conf" + java.io.File.separator + "collector.conf")
}

object TestConfig extends App {
  CollectorConfig
  CollectorConfig.something
}
