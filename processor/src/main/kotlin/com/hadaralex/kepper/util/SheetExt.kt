package com.hadaralex.kepper.util

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

/**
 * Returns the index of the first row that's empty.
 *
 * It will check against first column, so if that column is empty,
 * the count will end.
 */
internal val Sheet.indexOfFirstEmptyRow: Int
    get() {
        var index = 0
        val rowIterator: Iterator<Row> = rowIterator()
        while (rowIterator.hasNext()) {
            val row: Row = rowIterator().next()
            val cell = row.firstOrNull()
            if (cell.isEmpty) {
                break
            }
            index++
        }
        return index
    }
