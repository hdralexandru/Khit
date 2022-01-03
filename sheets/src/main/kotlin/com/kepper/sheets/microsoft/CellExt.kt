package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperCell
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

internal val Cell.toKepperCell: KepperCell
    get() = when (this.cellType) {
        CellType.BOOLEAN -> KepperCell.TypeBoolean(this.booleanCellValue)
        CellType.STRING -> KepperCell.TypeString(this.stringCellValue)
        CellType.NUMERIC -> KepperCell.TypeDouble(this.numericCellValue)
        else -> KepperCell.Unsupported
    }