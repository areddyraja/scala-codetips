package com.akrantha.access.db.main

import com.akrantha.access.db.CassandraDataAccessManager
import com.akrantha.config.MyAppConfig

/**
 * @author reddyraja
 */
object CassandraMainTest extends App {
  CassandraDataAccessManager startWith MyAppConfig
  
}