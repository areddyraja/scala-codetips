package com.akrantha.config

import java.io.File

/**
 * @author reddyraja
 */
object MyAppConfig extends AppConfig{
  def configFile: File = new File(sys.props("user.dir") + java.io.File.separator + "conf" + java.io.File.separator + "appconfig.conf")
  def main( args:Array[String] ):Unit = {
    args foreach println
    println(sys.props("user.dir"))
    println(MyAppConfig.get("sankri_server.interface", "default"))
  } 
}
