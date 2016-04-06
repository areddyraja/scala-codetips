package com.akrantha.access.db.dataobjects

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Table
import java.lang.Integer
import java.lang.Long
import java.lang.String
import java.math.BigDecimal
import java.net.InetAddress
import scala.beans.BeanProperty
import java.util.UUID
import com.akrantha.access.db.CassandraDataAccessObject

/**
 * @author reddyraja
 */
@Table(name = "raw_data_events_day")
class RawDataEventDHM(
    var key: String,
    var day_epoch: Long,
    var data: String) extends CassandraDataAccessObject {

  override def keyspace = "kedi_raw_data"

}