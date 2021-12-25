package com.hadaralex.kepper

data class MyClass(val x: Int)

internal object Main {

    private const val DATA_FILE_PATH: String = "data/products.xlsx"

    data class Nested(val id: String)


    @JvmStatic
    fun main(args: Array<String>) {
        val listA = Array(4) { it }

        val listB: Array<Int> = listA
        println("ListA: $listA")
        println("ListB: $listB")

        println("After")
        println("ListA: $listA")
        println("ListB: $listB")

    }
}