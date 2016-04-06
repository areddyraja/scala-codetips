package com.akrantha.config

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import scala.util.Try
import collection.JavaConversions._

/**
 * @author reddyraja
 */
trait Configuration {

  private val Log = LoggerFactory getLogger getClass
  def config: Config
  private implicit def toInt(instance: Any) = Integer.parseInt(String valueOf instance)
 
  def of(property: String) = {
    try { config getString property } catch {
      case t: Throwable ⇒ {
        Log warn ("Could not find property [" + property + "] due to [" + t.toString + "]")
        ""
      }
    }
  }

  def getInt(property: String): Int = {
    try { config getString property } catch {
      case t: Throwable ⇒ {
        Log warn ("Could not find property [" + property + "] due to [" + t.toString + "]")
        1000
      }
    }
  }
  
  def get(property: String, default: String) = {
    try { config getString property } catch {
      case t: Throwable ⇒ {
        Log warn ("No property with Missiong [" + property + "] - [" + t.toString + "]")
        default
      }
    }
  }

  def get(property: String, default: Int) = {
    try { config getInt property } catch {
      case t: Throwable ⇒ {
        Log warn ("Could not find property [" + property + "] due to [" + t.toString + "]")
        default
      }
    }
  }

  def getMap(property: String) = {
    try {
      val value = config getString property
      value.split(',').map(kv => {
        val pair = kv.split("->")
        (pair(0).trim(), pair(1).trim())
      }).toMap
    } catch {
      case t: Throwable ⇒ {
        Log warn ("Could not find property [" + property + "] due to [" + t.toString + "]")
        Map[String, String]()
      }
    }
  }

  def exists(property: String) = {
    try {
      config getString property
      true
    } catch {
      case t: Throwable ⇒ false
    }
  }

  def getStringList(property: String) = {
    Try(config getStringList property toList).getOrElse(List[String]())
  }

}