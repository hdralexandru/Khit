package com.hadaralex.kepper.util

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

internal val Row.indexOfFirstEmptyCell: Int
    get() {
        var index: Int = 0
        val cellIterator: Iterator<Cell> = cellIterator()
        while (cellIterator.hasNext()) {
            val cell = cellIterator.next()
            if (cell.isEmpty) {
                break
            }
            index++
        }
        return index
    }