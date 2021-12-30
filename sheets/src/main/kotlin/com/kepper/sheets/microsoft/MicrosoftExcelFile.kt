package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperFile
import com.kepper.commons.model.KepperPage
import com.kepper.sheets.utils.buildList
import org.apache.poi.xssf.usermodel.XSSFWorkbook

internal class MicrosoftExcelFile internal constructor(
    private val workbook: XSSFWorkbook,
    private val pageTranslator: MicrosoftSheetTranslator,
) : KepperFile {
    override val pagesCount: Int get() = workbook.numberOfSheets

    override fun pages(): List<KepperPage> = buildList {
        val sheetIterator = workbook.sheetIterator()
        while (sheetIterator.hasNext()) {
            val page = pageTranslator.translate(sheetIterator.next())
            add(page)
        }
    }
}