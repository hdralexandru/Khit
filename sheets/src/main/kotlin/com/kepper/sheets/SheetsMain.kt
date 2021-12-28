package com.kepper.sheets

import kotlinx.coroutines.runBlocking

internal object SheetsMain {
    private const val SHEET_PATH: String = "data/products.xlsx"

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = KepperFileReader {
            path = AbsolutFilePath(SHEET_PATH)
            type = FileType.MICROSOFT
        }

        runBlocking {
            val list = reader.loadFile().pageList()
            println("list size: ${list.size}")
            list.forEach { println(it) }
        }
    }
}