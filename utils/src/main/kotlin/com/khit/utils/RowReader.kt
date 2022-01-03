package com.khit.utils

import com.khit.commons.exceptions.WrongCellTypeException
import com.khit.commons.model.KhitCell
import com.khit.commons.model.KhitRow
import com.khit.commons.utils.KhitCellType

object RowReader {

    fun readDoubleOrNull(row: KhitRow, index: Int): Double? = when (val cell = row.getContentAt(index)) {
        is KhitCell.TypeDouble -> cell.doubleValue
        else -> null
    }

    fun readStringOrNull(row: KhitRow, index: Int): String? = when (val cell = row.getContentAt(index)) {
        is KhitCell.TypeString -> cell.stringValue
        else -> null
    }

    fun readBooleanOrNull(row: KhitRow, index: Int): Boolean? = when (val cell = row.getContentAt(index)) {
        is KhitCell.TypeBoolean -> cell.booleanValue
        else -> null
    }

    fun readDoubleOrThrow(row: KhitRow, index: Int, defaultValue: Double? = null): Double {
        return readOrThrow(row, index, defaultValue, KhitCell.TypeDouble.CELL_TYPE) {
            (it as? KhitCell.TypeDouble)?.doubleValue
        }
    }

    fun readStringOrThrow(row: KhitRow, index: Int, defaultValue: String? = null): String {
        return readOrThrow(row, index, defaultValue, KhitCell.TypeString.CELL_TYPE) {
            (it as? KhitCell.TypeString)?.stringValue
        }
    }

    fun readBooleanOrThrow(row: KhitRow, index: Int, defaultValue: Boolean? = null): Boolean {
        return readOrThrow(row, index, defaultValue, KhitCell.TypeBoolean.CELL_TYPE) {
            (it as? KhitCell.TypeBoolean)?.booleanValue
        }
    }

    private inline fun <T> readOrThrow(
        row: KhitRow,
        index: Int,
        defaultValue: T?,
        typeExpected: KhitCellType,
        crossinline matches: (KhitCell) -> T?,
    ): T = row.getContentAt(index).let { cell ->
        matches(cell)
            ?: defaultValue
            ?: throw WrongCellTypeException("Expected $typeExpected, but got ${cell.type} or it is empty.")
    }
}