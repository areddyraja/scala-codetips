package com.akantha.config

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

/**
 * @author reddyraja
 */
trait Configuration {
  
  private val Log = LoggerFactory getLogger getClass
  def config: Config
  private implicit def toInt(instance: Any) = Integer.parseInt(String valueOf instance)
  def get(property: String, default: String) = {
    try { config getString property } catch {
      case t: Throwable ⇒ {
        Log warn ("No property with Missiong [" + property + "] - [" + t.toString + "]")
        default
      }
    }
  }

}