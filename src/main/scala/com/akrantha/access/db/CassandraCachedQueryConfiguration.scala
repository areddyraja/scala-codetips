package com.akrantha.access.db

import scala.collection.mutable.Map
import scala.util.{ Failure, Success }
import org.slf4j.LoggerFactory
import com.datastax.driver.core.PreparedStatement
import com.datastax.driver.mapping.MappingSession
import com.akrantha.config.Configuration

/**
 * @author reddyraja
 */
object CassandraCachedQueryConfiguration {
  private val Log = LoggerFactory getLogger getClass

  private val PreparedStatementCache = Map[String, CachedPreparedStatement]()

  def read(implicit config: Configuration) = {
    (1 to 50) foreach {
      index ⇒
        val Prefix = "vendor" + "." + "queries" + "." + index
        if (config exists (Prefix + "." + "name")) {
          val name = config of (Prefix + "." + "name")
          val CQL = config of (Prefix + "." + "cql")
          val keyspace = config of (Prefix + "." + "keyspace")

          (CassandraDataAccessManager getSession keyspace) match {
            case Success(session) ⇒
              try {
                PreparedStatementCache += ((name, new CachedPreparedStatement(keyspace, session, session.getSession.prepare(CQL))))
              } catch {
                case t: Throwable ⇒ Log warn ("Could not cache prepared statement for query: name=" + name + ", query='" + CQL + "'", t)
              }
            case Failure(t) ⇒ Log warn ("Could not cache prepared statement for query: name=" + name + ", query='" + CQL + "'", t)
          }
        }
    }
  }

  def get(namedQuery: String) = PreparedStatementCache(namedQuery)
}

class CachedPreparedStatement(val keyspace: String, val session: MappingSession, val preparedStatement: PreparedStatement)