package com.khit.sheets.microsoft

import com.khit.commons.model.KhitCell
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType

/**
 * Returns true if the cell is empty
 *
 * It is considered empty even if it is of string type, but contains a blank string
 */
internal val Cell?.isEmpty: Boolean
    get() = this == null
        || cellType == CellType.BLANK
        || cellType == CellType._NONE
        || (cellType == CellType.STRING && stringCellValue.isBlank())

internal val Cell.toKhitCell: KhitCell
    get() = when (this.cellType) {
        CellType.BOOLEAN -> KhitCell.TypeBoolean(this.booleanCellValue)
        CellType.STRING -> KhitCell.TypeString(this.stringCellValue)
        CellType.NUMERIC -> KhitCell.TypeDouble(this.numericCellValue)
        else -> KhitCell.Unsupported
    }