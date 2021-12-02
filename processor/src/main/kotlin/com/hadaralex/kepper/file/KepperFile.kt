package com.hadaralex.kepper.file

import com.hadaralex.kepper.excel.ExcelSheetIterator
import org.apache.poi.xssf.usermodel.XSSFWorkbook

// For excels only. Libre will need another variant
class KepperFile internal constructor(private val workBook: XSSFWorkbook) {

    fun sheetIterator(): Iterator<KepperSheet> {
        return ExcelSheetIterator(workBook.sheetIterator())
    }

    fun close() {
        workBook.close()
    }
}