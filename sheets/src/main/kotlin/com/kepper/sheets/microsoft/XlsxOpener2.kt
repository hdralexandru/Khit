package com.kepper.sheets.microsoft

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class XlsxOpener2(private val filename: String) {
    fun open() {
        val workbook: Workbook = XSSFWorkbook(filename)

        workbook.sheetIterator().forEach {

//            val nrOfRows = it.indexOfFirstEmptyRow
//            val nrOfColumns = it.first().lastCellNum
//            logMe { "rows: $nrOfRows, columns: $nrOfColumns" }
        }
    }
}