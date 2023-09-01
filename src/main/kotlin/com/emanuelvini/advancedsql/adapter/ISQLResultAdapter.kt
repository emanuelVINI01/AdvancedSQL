package com.emanuelvini.advancedsql.adapter

import com.emanuelvini.advancedsql.result.ISimpleResultSet

@FunctionalInterface
interface ISQLResultAdapter<T> {

    fun adapt (resultSet : ISimpleResultSet) : T

}