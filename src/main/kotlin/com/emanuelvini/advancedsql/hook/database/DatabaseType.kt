package com.emanuelvini.advancedsql.hook.database

import java.sql.Connection

interface DatabaseType {

    fun connect() : Connection

}