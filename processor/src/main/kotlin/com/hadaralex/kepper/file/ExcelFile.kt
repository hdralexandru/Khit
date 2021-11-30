package com.hadaralex.kepper.file

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExcelFile internal constructor(
    private val workBook: XSSFWorkbook,
    private val autoClose: Boolean = true,
) {
    /**
     * Selects the sheet with the name [sheetName]
     */
    fun selectSheet(sheetName: String) {

    }

    /**
     * Selects the sheet with the name for which [func] returns true.
     * We iterate through each sheet and pass you the name of the sheet
     *
     *
     * When the first `
     */

    fun selectSheet(func: (String) -> Boolean) {
        val iterator: Iterator<Sheet> = workBook.sheetIterator()
        var selectedSheet: Sheet? = null
        for (sheet in iterator) {
             if (func(sheet.sheetName)) {
                 selectedSheet = sheet
                 break
             }
        }

        if (selectedSheet == null) {
            workBook.close()
            throw SheetNotFoundException("No sheet was found.")
        } else {

        }
    }
}