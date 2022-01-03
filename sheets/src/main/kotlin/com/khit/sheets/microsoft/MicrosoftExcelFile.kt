package com.khit.sheets.microsoft

import com.khit.commons.model.KhitFile
import com.khit.commons.model.KhitPage
import com.khit.sheets.utils.buildList
import org.apache.poi.xssf.usermodel.XSSFWorkbook

internal class MicrosoftExcelFile internal constructor(
    private val workbook: XSSFWorkbook,
    private val pageTranslator: MicrosoftSheetTranslator,
) : KhitFile {
    override val pagesCount: Int get() = workbook.numberOfSheets

    override fun pages(): List<KhitPage> = buildList {
        val sheetIterator = workbook.sheetIterator()
        while (sheetIterator.hasNext()) {
            val page = pageTranslator.translate(sheetIterator.next())
            add(page)
        }
    }

    override fun close() {
        workbook.close()
    }
}