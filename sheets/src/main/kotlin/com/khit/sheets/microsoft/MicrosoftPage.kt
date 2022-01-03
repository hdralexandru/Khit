package com.khit.sheets.microsoft

import com.khit.commons.model.KhitPage
import com.khit.commons.model.KhitRow
import com.khit.commons.model.PageHeader
import com.khit.sheets.common.CommonPageHeader
import org.apache.poi.ss.usermodel.Sheet

data class MicrosoftPage(private val sheet: Sheet) : KhitPage {
    override val sheetName: String get() = sheet.sheetName

    override val header: PageHeader = CommonPageHeader.build(sheet.getRow(0))
    override val dataRowIterator: Iterator<KhitRow> get() = RowIterator(sheet)

    private class RowIterator(private val sheet: Sheet) : Iterator<KhitRow> {
        private var index: Int = 1

        override fun hasNext(): Boolean {
            val isEmpty = sheet.getRow(index + 1)?.getCell(0)?.isEmpty ?: true
            return !isEmpty
        }

        override fun next(): KhitRow {
            val nextRow = sheet.getRow(index)
            index += 1
            return MicrosoftRow(nextRow)
        }
    }
}