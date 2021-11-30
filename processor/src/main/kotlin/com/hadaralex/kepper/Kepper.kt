package com.hadaralex.kepper

import com.hadaralex.kepper.file.ExcelFile
import java.io.FileNotFoundException
import kotlin.jvm.Throws
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Kepper {
    class Builder() {
        private var autoClose: Boolean = true
        private var path: String? = null

        // Note: when kontracts are stable, migrate to them
        @Throws(IllegalArgumentException::class)
        private fun checkParams() {
            if (path == null) {
                throw IllegalArgumentException("Missing path to the com.hadaralex.kepper.file!")
            }
        }

        fun forFile(filePath: String): Builder = apply {
            path = filePath
        }

        fun autoClose(value: Boolean = true) : Builder = apply {
            autoClose = value
        }

        @Throws(FileNotFoundException::class)
        fun build(): ExcelFile {
            checkParams()

            val workBook = XSSFWorkbook(path!!)

            return ExcelFile(workBook, autoClose)
        }
    }
}