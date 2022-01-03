package com.khit.sheets.common

import com.khit.commons.NO_HEADER
import com.khit.commons.model.PageHeader
import com.khit.commons.model.TableHeaderCell
import com.khit.sheets.microsoft.isEmpty
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row


internal class CommonPageHeader(
    private val headers: List<TableHeaderCell>,
) : PageHeader {
    override val size: Int get() = headers.size

    override fun indexOf(header: TableHeaderCell): Int {
        val index = headers.indexOfFirst { it == header }
        return if (index == -1) NO_HEADER else index
    }

    internal companion object {
        fun build(row: Row?): CommonPageHeader {
            val iterator: Iterator<Cell> = row?.cellIterator() ?: return CommonPageHeader(emptyList())

            val headers: MutableList<String> = mutableListOf()
            while (iterator.hasNext()) {
                val cell = iterator.next()

                if (cell.isEmpty) return CommonPageHeader(headers)

                headers.add(cell.stringCellValue)
            }
            return CommonPageHeader(headers)
        }
    }
}