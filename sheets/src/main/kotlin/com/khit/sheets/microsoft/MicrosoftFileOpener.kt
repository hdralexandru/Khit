package com.khit.sheets.microsoft

import com.khit.commons.model.KhitFile
import com.khit.sheets.AbsolutFilePath
import com.khit.sheets.common.FileOpener
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

internal class MicrosoftFileOpener : FileOpener {
    private var workbook: Workbook? = null
    private val translator: MicrosoftSheetTranslator = MicrosoftSheetTranslator()

    @Throws(Exception::class)
    override suspend fun open(filepath: AbsolutFilePath): KhitFile {
        return withContext(Dispatchers.IO) {
            val result = runCatching<MicrosoftExcelFile> {
                val vb = XSSFWorkbook(filepath.filepath)
                this@MicrosoftFileOpener.workbook = vb
                MicrosoftExcelFile(vb, translator)
            }

            return@withContext result.getOrNull() ?: throw IOException(result.exceptionOrNull())
        }
    }
}