package com.akrantha.access.db

import org.slf4j.LoggerFactory
import com.akrantha.config.Configuration

/**
 * @author reddyraja
 */
object CassandraDataStoreConfig {
  private val Log = LoggerFactory getLogger getClass
  var configuration: Option[Configuration] = None
  
  def node = (dataStoreConfig get (("cassandra" + "." + "node"), "localhost")) split "," filter (each ⇒ each.trim.size > 0)
  def fetchSize = Integer.parseInt(dataStoreConfig get ("cassandra" + "." + "fetch_size", "1000"))
  def maxConnectionsPerHost = dataStoreConfig get (("cassandra" + "." + "MaxConnectionsPerHost"), 256)
  val DefaultRetryPolicy = com.datastax.driver.core.policies.DefaultRetryPolicy.INSTANCE

  def withConfig(config: Configuration) = configuration = Some(config)

  def keyspaces = {
    val _keyspaces = dataStoreConfig get (("cassandra" + "." + "keyspaces"), "")
    if ("" equals _keyspaces) Array[String]() else (_keyspaces split ",") map (each ⇒ each.trim)
  }

  lazy val ConsistencyLevel = {
    val configuredConsistency = dataStoreConfig.get(("cassandra" + "." + "consistency"), "ALL ")
    configuredConsistency.trim match {
      case "ALL" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.ALL
        com.datastax.driver.core.ConsistencyLevel.ALL
      }

      case "ANY" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.ANY
        com.datastax.driver.core.ConsistencyLevel.ANY
      }

      case "EACH_QUORUM" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM
        com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM
      }

      case "LOCAL_ONE" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.LOCAL_ONE
        com.datastax.driver.core.ConsistencyLevel.LOCAL_ONE
      }

      case "LOCAL_QUORUM" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.LOCAL_QUORUM
        com.datastax.driver.core.ConsistencyLevel.LOCAL_QUORUM
      }

      case "LOCAL_SERIAL" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL
        com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL
      }

      case "ONE" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.ONE
        com.datastax.driver.core.ConsistencyLevel.ONE
      }

      case "QUORUM" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.QUORUM
        com.datastax.driver.core.ConsistencyLevel.QUORUM
      }

      case "SERIAL" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.SERIAL
        com.datastax.driver.core.ConsistencyLevel.SERIAL
      }

      case "THREE" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.THREE
        com.datastax.driver.core.ConsistencyLevel.THREE
      }

      case "TWO" ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.TWO
        com.datastax.driver.core.ConsistencyLevel.TWO
      }

      case _ ⇒ {
        Log info "Using ConsistencyLevel = " + com.datastax.driver.core.ConsistencyLevel.ALL
        com.datastax.driver.core.ConsistencyLevel.ALL
      }
    }
  }

  def dataStoreConfig = configuration.get

}