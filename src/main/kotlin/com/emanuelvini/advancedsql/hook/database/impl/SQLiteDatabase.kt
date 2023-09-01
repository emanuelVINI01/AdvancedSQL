package com.emanuelvini.advancedsql.hook.database.impl

import com.emanuelvini.advancedsql.hook.database.DatabaseType
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

class SQLiteDatabase (
    private val file : File
) : DatabaseType {
    override fun connect(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:" + file.absolutePath)
    }
}