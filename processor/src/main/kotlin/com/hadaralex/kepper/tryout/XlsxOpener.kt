package com.hadaralex.kepper.tryout

import com.hadaralex.kepper.util.logMe
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class XlsxOpener(private val filename: String) {
    fun open() {
        val workbook: XSSFWorkbook = XSSFWorkbook(filename)
        workbook.sheetIterator().forEach {
            logMe { "Sheetname: ${it.sheetName}" }
        }
    }
}