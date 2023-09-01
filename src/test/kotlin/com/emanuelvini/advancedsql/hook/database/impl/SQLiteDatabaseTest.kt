package com.emanuelvini.advancedsql.hook.database.impl

import com.emanuelvini.advancedsql.adapter.TestAdapter
import com.emanuelvini.advancedsql.hook.executor.DatabaseExecutor
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.expect

internal class SQLiteDatabaseTest {

    private var databaseExecutor : DatabaseExecutor? = null

    private val adapter : TestAdapter = TestAdapter()

    @Test
    fun testConnection() {
        val file = File("tests.db")
        val databaseType = SQLiteDatabase(file)
        val connection = databaseType.connect()
        assertNotEquals(connection, null)
        databaseExecutor = DatabaseExecutor(connection)
        assertNotEquals(databaseExecutor, null)



        testUpdate()
        testSelectOne()
        testSelectMany()
    }

    private fun testUpdate() {
        databaseExecutor!!.update("CREATE TABLE IF NOT EXISTS ads_provider (id INT)")
        databaseExecutor!!.update("INSERT INTO ads_provider VALUES (1)")
        databaseExecutor!!.update("INSERT INTO ads_provider VALUES (2)")
    }

    private fun testSelectOne() {
        val data = databaseExecutor!!.selectOne(
            "SELECT * FROM ads_provider WHERE id = ?",
            {
                it.setInt(1, 1)
            },
            adapter
        )
        println("Data ID: $data")
    }


    private fun testSelectMany() {
        val data = databaseExecutor!!.selectMany(
            "SELECT * FROM ads_provider",
            adapter
        )
        data.forEach {
            println("Data ID: $it")
        }
    }


}