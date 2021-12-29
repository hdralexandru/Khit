package com.kepper.adapters.utils

import com.kepper.commons.exceptions.WrongCellTypeException
import com.kepper.commons.model.KepperCell
import com.kepper.commons.model.KepperRow
import com.kepper.commons.utils.KepperCellType

internal object RowReader {

    fun readDoubleOrThrow(row: KepperRow, index: Int, defaultValue: Double? = null): Double {
        return readOrThrow(row, index, defaultValue, KepperCell.TypeDouble.CELL_TYPE) {
            (it as? KepperCell.TypeDouble)?.doubleValue
        }
    }

    fun readStringOrThrow(row: KepperRow, index: Int, defaultValue: String? = null): String {
        return readOrThrow(row, index, defaultValue, KepperCell.TypeString.CELL_TYPE) {
            (it as? KepperCell.TypeString)?.stringValue
        }
    }

    fun readBooleanOrThrow(row: KepperRow, index: Int, defaultValue: Boolean? = null): Boolean {
        return readOrThrow(row, index, defaultValue, KepperCell.TypeBoolean.CELL_TYPE) {
            (it as? KepperCell.TypeBoolean)?.booleanValue
        }
    }

    private inline fun <T> readOrThrow(
        row: KepperRow,
        index: Int,
        defaultValue: T?,
        typeExpected: KepperCellType,
        crossinline matches: (KepperCell) -> T?,
    ): T = row.getContentAt(index).let { cell ->
        matches(cell)
            ?: defaultValue
            ?: throw WrongCellTypeException("Expected $typeExpected, but got ${cell.type} or it is empty.")
    }
}