package com.khit.sheets.microsoft

import com.khit.commons.model.KhitCell
import com.khit.commons.model.KhitRow
import org.apache.poi.ss.usermodel.Row

class MicrosoftRow(private val row: Row) : KhitRow {

    override fun getContentAt(index: Int): KhitCell {
        if (index < 0) return KhitCell.Empty
        val cell = row.getCell(index) ?: return KhitCell.Empty

        return cell.toKhitCell
    }
}