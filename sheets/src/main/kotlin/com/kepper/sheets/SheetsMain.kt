package com.kepper.sheets

import com.kepper.commons.model.KepperCell
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
            val list = reader.loadFile().pages()
            val firstPage = list.first()
            println("list size: ${list.size}")

            println("Headers: ${firstPage.header}")
            val headerSize = firstPage.header.size
            println("Header size: $headerSize")
            firstPage.dataRowIterator.forEachRemaining {
                println("--- New Row ---")
                for (i in 0 until headerSize) {
                    print(" ${it.getContentAt(i).valueType} | ")
                }
                println("\n---------------")
            }
        }
    }

    private val KepperCell.valueType: String
        get() = when (this) {
            KepperCell.Empty -> "empty"
            is KepperCell.TypeBoolean -> this.booleanValue.toString()
            is KepperCell.TypeDouble -> this.doubleValue.toString()
            is KepperCell.TypeString -> this.stringValue
            KepperCell.Unsupported -> "unsupported"
        }
}