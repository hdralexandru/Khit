package com.hadaralex.kepper.excel.utils

import com.hadaralex.kepper.excel.ExcelSheet
import com.hadaralex.kepper.file.KepperSheet
import org.apache.poi.ss.usermodel.Sheet

internal fun Sheet.toKepperSheet(): KepperSheet {
    return ExcelSheet(sheetName = this.sheetName)
}