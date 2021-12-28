package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperFile
import com.kepper.sheets.AbsolutFilePath
import com.kepper.sheets.common.FileOpener
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

internal class MicrosoftFileOpener : FileOpener {
    private var workbook: Workbook? = null
    private val translator: MicrosoftSheetTranslator = MicrosoftSheetTranslator()

    // TODO don't forget to call close()
    @Throws(Exception::class)
    override suspend fun open(filepath: AbsolutFilePath): KepperFile {
        return withContext(Dispatchers.IO) {
            val result = runCatching<MicrosoftExcelFile> {
                val vb = XSSFWorkbook(filepath.filepath)
                this@MicrosoftFileOpener.workbook = vb
                MicrosoftExcelFile(vb, translator)
            }

            return@withContext result.getOrNull() ?: throw IOException(result.exceptionOrNull())
        }
    }

    override fun close() {
        workbook?.close()
    }
}