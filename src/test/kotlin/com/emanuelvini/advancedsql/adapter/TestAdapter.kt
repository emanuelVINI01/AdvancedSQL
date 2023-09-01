package com.emanuelvini.advancedsql.adapter

import com.emanuelvini.advancedsql.result.ISimpleResultSet

class TestAdapter : ISQLResultAdapter<Int> {
    override fun adapt(resultSet: ISimpleResultSet): Int {
        return resultSet.get("id")
    }

}