package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperPage
import com.kepper.commons.model.KepperRow
import com.kepper.commons.model.PageHeader
import com.kepper.sheets.common.CommonPageHeader
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

data class MicrosoftPage(private val sheet: Sheet) : KepperPage {
    override val sheetName: String get() = sheet.sheetName

    override val header: PageHeader = CommonPageHeader.build(sheet.getRow(0))
    override val dataRowIterator: Iterator<KepperRow> get() = RowIterator(sheet.rowIterator())

    private class RowIterator(private val rowIterator: Iterator<Row>) : Iterator<KepperRow> {
        override fun hasNext(): Boolean = rowIterator.hasNext()

        override fun next(): KepperRow = MicrosoftRow(rowIterator.next())
    }
}