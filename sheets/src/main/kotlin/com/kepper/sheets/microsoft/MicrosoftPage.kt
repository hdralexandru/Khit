package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperPage
import com.kepper.commons.model.KepperRow
import com.kepper.commons.model.PageHeader
import com.kepper.sheets.common.CommonPageHeader
import org.apache.poi.ss.usermodel.Sheet

data class MicrosoftPage(private val sheet: Sheet) : KepperPage {
    override val sheetName: String get() = sheet.sheetName

    override val header: PageHeader = CommonPageHeader.build(sheet.getRow(0))
    override val dataRowIterator: Iterator<KepperRow> get() = RowIterator(sheet)

    private class RowIterator(private val sheet: Sheet) : Iterator<KepperRow> {
        private var index: Int = 1

        override fun hasNext(): Boolean {
            val isEmpty = sheet.getRow(index + 1)?.getCell(0)?.isEmpty ?: true
            return !isEmpty
        }

        override fun next(): KepperRow {
            val nextRow = sheet.getRow(index)
            index += 1
            return MicrosoftRow(nextRow)
        }
    }
}