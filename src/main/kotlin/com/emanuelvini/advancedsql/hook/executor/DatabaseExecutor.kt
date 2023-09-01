package com.emanuelvini.advancedsql.hook.executor


import com.emanuelvini.advancedsql.adapter.ISQLResultAdapter
import com.emanuelvini.advancedsql.result.ISimpleResultSet
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.function.Consumer

class DatabaseExecutor (
    private val connection : Connection
) {

    fun <T> selectOne(query : String, statement : Consumer<PreparedStatement>, adapter : ISQLResultAdapter<T>) : T {
        val preparedStatement = connection.prepareStatement(query)
        statement.accept(
            preparedStatement
        )
        val resultSet = preparedStatement.executeQuery()!!
        val simpleResult = object : ISimpleResultSet {
            override fun <T> get(key: String): T {
                return resultSet.getObject(key)!! as T
            }

            override fun next(): Boolean {
                return resultSet.next()
            }
        }
        simpleResult.next()

        val result = adapter.adapt(
            simpleResult
        )
        preparedStatement.close()
        return result
    }


    fun <T> selectMany(query : String, statement : Consumer<PreparedStatement>, adapter : ISQLResultAdapter<T>) : List<T> {
        val preparedStatement = connection.prepareStatement(query)
        statement.accept(
            preparedStatement
        )
        val resultSet = preparedStatement.executeQuery()!!
        val simpleResult = object : ISimpleResultSet {
            override fun <T> get(key: String): T {
                return resultSet.getObject(key)!! as T
            }

            override fun next(): Boolean {
                return resultSet.next()
            }
        }
        val results = mutableListOf<T>()
        while (simpleResult.next()) {
            results.add(adapter.adapt(
                simpleResult
            ))
        }
        preparedStatement.close()
        return results.toList()
    }

    fun <T> selectMany(query : String, adapter : ISQLResultAdapter<T>) : List<T> {
        return selectMany(query, {}, adapter)

    }

    fun update(query : String, statement : Consumer<PreparedStatement>) {
        val preparedStatement = connection.prepareStatement(query)
        statement.accept(
            preparedStatement
        )
        preparedStatement.executeUpdate()
        preparedStatement.close()
    }

    fun update(query : String) {
        update(query) {}
    }

}