package com.hadaralex.kepper.util

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

