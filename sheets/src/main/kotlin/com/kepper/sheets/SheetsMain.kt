package com.kepper.sheets

internal object SheetsMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val reader = KepperFileReader {
            path = AbsolutFilePath("/data/products.xlsx")
            type = FileType.MICROSOFT
        }
    }
}