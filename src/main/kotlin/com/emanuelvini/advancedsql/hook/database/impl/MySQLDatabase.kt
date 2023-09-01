package com.emanuelvini.advancedsql.hook.database.impl

import com.emanuelvini.advancedsql.hook.database.DatabaseType
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection


class MySQLDatabase (
    private val host : String,
    private val username : String,
    private val password : String,
    private val database : String
) : DatabaseType {
    override fun connect(): Connection {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://$host/$database"
        config.username = username
        config.password = password

        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "500")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "4096")

        val ds = HikariDataSource(config)
        return ds.connection!!
    }
}