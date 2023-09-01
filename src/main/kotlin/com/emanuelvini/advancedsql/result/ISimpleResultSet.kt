package com.emanuelvini.advancedsql.result

interface ISimpleResultSet {

    fun <T> get(key : String) : T

    fun next() : Boolean
}