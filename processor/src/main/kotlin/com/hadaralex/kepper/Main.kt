package com.hadaralex.kepper

import com.hadaralex.kepper.tryout.XlsxOpener
import com.hadaralex.kepper.util.logMe

internal object Main {
    private const val DATA_FILE_PATH: String = "data/products.xlsx"

    @JvmStatic
    fun main(args: Array<String>) {
        logMe { "Starting processor-main" }
        XlsxOpener(DATA_FILE_PATH).open()
    }
}