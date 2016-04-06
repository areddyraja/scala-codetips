package com.akrantha.access.db

import scala.util.{ Failure, Success, Try }
import org.slf4j.LoggerFactory
import com.datastax.driver.core.Cluster
import com.datastax.driver.mapping.MappingSession
import com.akrantha.config.MyAppConfig
import com.akrantha.config.Configuration
import com.datastax.driver.core.PoolingOptions
import com.datastax.driver.core.PoolingOptions
import com.datastax.driver.core.HostDistance
import scala.collection.JavaConverters
import scala.collection.JavaConversions

/**
 * @author reddyraja
 */
object CassandraDataAccessManager {
  private val Log = LoggerFactory getLogger getClass

  private def ConfiguredPoolingOptions = {
    val poolingOptions = new PoolingOptions
    poolingOptions setMaxConnectionsPerHost (HostDistance.REMOTE, CassandraDataStoreConfig.maxConnectionsPerHost)
    poolingOptions setMaxConnectionsPerHost (HostDistance.LOCAL, CassandraDataStoreConfig.maxConnectionsPerHost)
    poolingOptions setCoreConnectionsPerHost (HostDistance.REMOTE, CassandraDataStoreConfig.maxConnectionsPerHost)
    poolingOptions setCoreConnectionsPerHost (HostDistance.LOCAL, CassandraDataStoreConfig.maxConnectionsPerHost)
  }

  private def createSessions = {
    try {
      val cluster = Cluster.builder.withPoolingOptions(ConfiguredPoolingOptions).addContactPoints(CassandraDataStoreConfig.node: _*).build
      Success(
        (CassandraDataStoreConfig.keyspaces map {
          keyspace ⇒
            {
              (keyspace, new MappingSession(keyspace, cluster connect keyspace, true))
            }
        }) toMap)
    } catch {
      case t: Throwable ⇒ {
        Log error ("Cannot connect to Cassandra due to:", t)
        Failure(t)
      }
    }
  }

  private lazy val sessions = createSessions

  def getSession(keyspace: String): Try[MappingSession] = {
    try {
      sessions match {
        case Success(_session) ⇒ Success(_session(keyspace))
        case Failure(t)        ⇒ Failure(t)
      }
    } catch {
      case t: Throwable ⇒ {
        Log error (("Cannot obtain session for keyspace " + keyspace), t)
        Failure(t)
      }
    }
  }

  def startWith(implicit configuration: Configuration) = {
    CassandraDataStoreConfig withConfig (configuration)
    CassandraDataStoreConfig.keyspaces map { eachKeyspace ⇒
      {
        CassandraDataAccessManager getSession eachKeyspace match {
          case Success(session)   ⇒ Log info ("Connected to keyspace:" + eachKeyspace)
          case Failure(exception) ⇒ Log error ("Cannot connect to keyspace:" + exception.getMessage)
        }
      }
    }
    createSessions
  }

  def shutdown = {
    CassandraDataStoreConfig.keyspaces map { eachKeyspace ⇒
      {
        CassandraDataAccessManager getSession eachKeyspace match {
          case Success(session) ⇒ {
            try {
              session.getSession.close
              Log info ("Closed session for keyspace: " + eachKeyspace)
            } catch {
              case t: Throwable ⇒ Log error ("Cannot close session to keyspace due to:" + (String valueOf t))
            }
          }
          case Failure(exception) ⇒ {
            Log warn ("Session was not open to keyspace: " + eachKeyspace + " due to:" + (String valueOf exception))
          }
        }
      }
    }
  }
}