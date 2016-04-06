package com.akrantha.access.db

import scala.collection.mutable.HashSet
import scala.collection.mutable.MutableList
import scala.util.{ Failure, Success, Try }
import com.datastax.driver.core.{ BatchStatement, BoundStatement, ResultSetFuture, Session }
import com.datastax.driver.core.BatchStatement.Type
import com.datastax.driver.mapping.option.{ BatchOptions, WriteOptions }
import com.datastax.driver.core.ResultSet
import org.slf4j.LoggerFactory

import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.FutureCallback
import com.datastax.driver.core.ResultSet
import scala.util.Success

/**
 * @author reddyraja
 */
trait CassandraDataAccessObject   extends Serializable {
  private val Log = LoggerFactory getLogger getClass

  private val writeOptions = new WriteOptions().setConsistencyLevel(CassandraDataStoreConfig.ConsistencyLevel)
    .setRetryPolicy(CassandraDataStoreConfig.DefaultRetryPolicy)

  def keyspace: String

  def save[T](writeOptions: WriteOptions): Try[CassandraDataAccessObject] = {
    (CassandraDataAccessManager getSession keyspace) match {
      case Success(session) ⇒ try {
        Success(session save (this, writeOptions))
      } catch {
        case t: Throwable ⇒ {
          Log error ("Save of " + this + " failed due to [" + t.toString + "]", t)
          Failure(t)
        }
      }

      case Failure(t) ⇒ {
        Log error ("Cannot save databject of type: " + this.getClass + " due to [" + t.toString + "]", t)
        Failure(t)
      }
    }
  }

  def saveAsync[T](writeOptions: WriteOptions): Try[ResultSetFuture] = {
    (CassandraDataAccessManager getSession keyspace) match {
      case Success(session) ⇒ try {
        Success(CassandraDataAccessObject executeGaurded ("saveAsync() for entity instance: " + this.toString, session saveAsync (this, writeOptions)))
      } catch {
        case t: Throwable ⇒ {
          Log error ("Save of " + this + " failed due to [" + t.toString + "]", t)
          Failure(t)
        }
      }

      case Failure(t) ⇒ {
        Log error ("Cannot save databject of type: " + this.getClass + " due to [" + t.toString + "]", t)
        Failure(t)
      }
    }
  }

  def save: Try[CassandraDataAccessObject] = {
    save(writeOptions)
  }

  def saveAsync: Try[ResultSetFuture] = {
    saveAsync(writeOptions)
  }

  def defaultWriteOptions = writeOptions
}

object CassandraDataAccessObject {
  private val Log = LoggerFactory getLogger getClass
  private val OnSuccess = (rows: ResultSet) ⇒ { Log info "Query executed successfully." }
  private val OnFailure = (query: String, error: Throwable) ⇒ {
    Log error ("Execution failed for query: " + query)
    Log error (error.getLocalizedMessage, error)
  }

  private val batchOptions = new BatchOptions().setConsistencyLevel(CassandraDataStoreConfig.ConsistencyLevel)
    .setRetryPolicy(CassandraDataStoreConfig.DefaultRetryPolicy)

  def countNamedQuery(query: String, parameters: Array[AnyRef]): Try[Long] = {
    val cachedStmt = CassandraCachedQueryConfiguration get query
    cachedStmt.preparedStatement setConsistencyLevel (CassandraDataStoreConfig.ConsistencyLevel)
    val boundStatement = new BoundStatement(cachedStmt.preparedStatement) /*bind parameters*/
    (0 to parameters.length - 1) foreach (index ⇒ setType(boundStatement, index, parameters(index)))
    val row = cachedStmt.session.getSession.execute(boundStatement).iterator.next
    if (null == row) Success(0) else Success(row getLong 0)
  }

  def executeGaurded(query: String, resultSetFuture: ResultSetFuture,
                     _onSuccess: (ResultSet ⇒ Unit) = OnSuccess, _onFailure: ((String, Throwable) ⇒ Unit) = OnFailure) = {
    Futures.addCallback(resultSetFuture, new FutureCallback[ResultSet]() {
      def onSuccess(resultSet: ResultSet) = {
        // Basically do nothing.
        _onSuccess(resultSet)
      }

      def onFailure(error: Throwable) = {
        _onFailure(query, error)
      }
    })
    resultSetFuture
  }
  
  def executeAndGetByNamedQuery(namedQuery: String, args: Array[AnyRef]): Try[ResultSet] = {
    val cachedstmt = CassandraCachedQueryConfiguration get namedQuery
    val session = cachedstmt.session.getSession
    val keyspace = cachedstmt.keyspace
    val stmt = new BoundStatement(cachedstmt.preparedStatement)
    Log info ("Setting bounds for statement: " + namedQuery)
    (0 to args.length - 1) foreach (index ⇒ setType(stmt, index, args(index)))

    if (null != session) {
      try {
        stmt.setFetchSize(CassandraDataStoreConfig.fetchSize)
        Success(session.execute(stmt))
      }
      catch {
        case t: Throwable ⇒ Failure(t)
      }
    }
    else {
      Failure(new RuntimeException("Session for the named query not found."))
    }
  }
def executeAsyncAndGetByNamedQuery(namedQuery: String, args: Array[AnyRef], _onSuccess: (ResultSet ⇒ Unit)) = {
    val cachedstmt = CassandraCachedQueryConfiguration get namedQuery
    val session = cachedstmt.session.getSession
    val keyspace = cachedstmt.keyspace
    val stmt = new BoundStatement(cachedstmt.preparedStatement)
    Log info ("Setting bounds for statement: " + namedQuery)
    (0 to args.length - 1) foreach (index ⇒ setType(stmt, index, args(index)))

    if (null != session) {
      try {
        stmt.setFetchSize(CassandraDataStoreConfig.fetchSize)
        executeGaurded(namedQuery, session.executeAsync(stmt), _onSuccess, OnFailure)
        Success(session.execute(stmt))
      }
      catch {
        case t: Throwable ⇒ Failure(t)
      }
    }
    else {
      Failure(new RuntimeException("Session for the named query not found."))
    }
  }
  
  private def executeBatchUpdateNamedQuery(queries: MutableList[Tuple2[String, Array[AnyRef]]], batchType: Type, asyncWay: Boolean): Unit = {
    val batch = new BatchStatement(batchType)
    var session: Session = null
    var keyspaces = new HashSet[String]
    queries foreach ({
      each ⇒
        {
          if (null != each && null != each._1 && null != each._2) {
            val cachedstmt = CassandraCachedQueryConfiguration.get(each._1)
            if (null == session) session = cachedstmt.session.getSession
            keyspaces += cachedstmt.keyspace
            val stmt = new BoundStatement(cachedstmt.preparedStatement)
            Log info ("Setting bounds for statement: " + each)
            (0 to each._2.length - 1) foreach (index ⇒ setType(stmt, index, each._2(index)))
            batch add stmt
          }
        }
    })

    batch.setConsistencyLevel(CassandraDataStoreConfig.ConsistencyLevel)

    if (1 == keyspaces.size) {
      if (asyncWay) {
        executeGaurded(queries.toString, session executeAsync (batch))
      }
      else {
        session execute (batch)
      }
    }
    else {
      if (1 > keyspaces.size) {
        Log error "Empty keyspaces or query list is empty, cannot execute batch."
      }
      else {
        Log error "Cannot execute batch for multiple keyspaces using single session bound to keyspace."
      }
    }
  }
  
  def executeBatchUpdateNamedQueryAsync(queries: MutableList[Tuple2[String, Array[AnyRef]]], batchType: Type): Unit = {
    executeBatchUpdateNamedQuery(queries, batchType, true)
  }

  def executeBatchUpdateNamedQuery(queries: MutableList[Tuple2[String, Array[AnyRef]]], batchType: Type): Unit = {
    executeBatchUpdateNamedQuery(queries, batchType, false)
  }
  
  def executeBatchSave(entities: Array[CassandraDataAccessObject]) = {
    if (0 != entities.length) {
      val _trySession = (CassandraDataAccessManager getSession entities(0).keyspace)
      _trySession match {
        case Success(session) ⇒ {
          val batchExecutor = session withBatch ()
          batchExecutor withOptions (batchOptions)
          entities foreach (batchExecutor.save(_))
        }
        case Failure(error) ⇒ {
          Log error ("Batch save failed due to:", error)
          Failure(error)
        }
      }
    }
    else {
    }
  }
  
  private def setType[T](stmt: BoundStatement, index: Int, @unchecked value: AnyRef) = {
    if (null == value) {
      stmt.setToNull(index)
    } else {
      if (value.isInstanceOf[java.lang.Long]) {
        stmt.setLong(index, value.asInstanceOf[java.lang.Long])
      } else if (value.isInstanceOf[java.lang.String]) {
        stmt.setString(index, value.asInstanceOf[java.lang.String])
      } else if (value.isInstanceOf[java.lang.Boolean]) {
        stmt.setBool(index, value.asInstanceOf[java.lang.Boolean])
      } else if (value.isInstanceOf[java.lang.Float]) {
        stmt.setFloat(index, value.asInstanceOf[java.lang.Float])
      } else if (value.isInstanceOf[java.lang.Double]) {
        stmt.setDouble(index, value.asInstanceOf[java.lang.Double])
      } else if (value.isInstanceOf[java.util.Date]) {
        stmt.setDate(index, value.asInstanceOf[java.util.Date])
      } else if (value.isInstanceOf[java.math.BigDecimal]) {
        stmt.setDecimal(index, value.asInstanceOf[java.math.BigDecimal])
      } else if (value.isInstanceOf[java.net.InetAddress]) {
        stmt.setInet(index, value.asInstanceOf[java.net.InetAddress])
      } else if (value.isInstanceOf[java.util.UUID]) {
        stmt.setUUID(index, value.asInstanceOf[java.util.UUID])
      } else if (value.isInstanceOf[java.util.List[java.lang.Long]]) {
        stmt.setList(index, value.asInstanceOf[java.util.List[java.lang.Long]])
      }
    }
  }

}