package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperFile
import com.kepper.commons.model.KepperPage
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

internal class MicrosoftExcelFile internal constructor(
    private val workbook: XSSFWorkbook,
    private val pageTranslator: MicrosoftSheetTranslator,
) : KepperFile {

    override val pagesCount: Int
        get() = TODO()

    override fun pageList(): List<KepperPage> = buildList {
        val sheetIterator = workbook.sheetIterator()
        while (sheetIterator.hasNext()) {
            val page = MicrosoftPage(sheetIterator.next())
            add(page)
        }
    }

    private class PagesIterator(
        private val sheetIterator: Iterator<Sheet>,
    ) : Iterator<KepperPage> {
        override fun hasNext(): Boolean = sheetIterator.hasNext()

        override fun next(): KepperPage {
            TODO()
        }
    }
}