package com.hadaralex.kepper

import com.hadaralex.kepper.file.KepperFile
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Kepper {

    fun openToRead(filePath: String): KepperFile {
        return KepperFile(XSSFWorkbook(filePath))
    }
}