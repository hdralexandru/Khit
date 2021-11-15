package com.hadaralex.kepper.tryout

import org.apache.poi.xssf.usermodel.XSSFWorkbook

class XlsxOpener(private val filename: String) {
    fun open() {
        val workbook: XSSFWorkbook = XSSFWorkbook(filename)

    }
}