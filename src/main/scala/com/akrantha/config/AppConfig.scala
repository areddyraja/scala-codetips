package com.akrantha.config

import java.io.File
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

/**
 * @author reddyraja
 */
trait AppConfig extends Configuration {

  /**
   *
   */
  def configFile: File

  var _config: Config = ConfigFactory parseFile configFile

  /**
   * The holder for all the application specific properties.
   */
  def config: Config = _config

}