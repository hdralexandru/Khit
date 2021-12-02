package com.hadaralex.kepper

internal object Main {
    private const val DATA_FILE_PATH: String = "data/products.xlsx"

    data class Nested(val id: String)

    fun foo(cls: Class<*>) {
        println(cls.name)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        foo(Nested::class.java)
    }
}