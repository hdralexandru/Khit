package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperCell
import com.kepper.commons.model.KepperRow
import org.apache.poi.ss.usermodel.Row

class MicrosoftRow(private val row: Row) : KepperRow {

    override fun getContentAt(index: Int): KepperCell {
        if (index < 0) return KepperCell.Empty
        val cell = row.getCell(index) ?: return KepperCell.Empty

        return cell.toKepperCell
    }
}