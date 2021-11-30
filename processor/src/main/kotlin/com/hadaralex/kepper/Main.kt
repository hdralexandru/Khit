package com.hadaralex.kepper

import com.hadaralex.kepper.tryout.XlsxOpener2
import com.hadaralex.kepper.util.logMe

internal object Main {
    private const val DATA_FILE_PATH: String = "data/products.xlsx"

    @JvmStatic
    fun main(args: Array<String>) {
        Kepper.withFile(DATA_FILE_PATH)
            .selectSheet()
    }
}