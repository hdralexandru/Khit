package com.kepper.sheets.common

import com.kepper.commons.NO_HEADER
import com.kepper.commons.model.PageHeader
import com.kepper.commons.model.TableHeaderCell
import com.kepper.sheets.utils.isEmpty
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

            val headers: List<String> = emptyList()
            while (iterator.hasNext()) {
                val cell = iterator.next()
                if (cell.isEmpty) return CommonPageHeader(headers)
            }
            return CommonPageHeader(emptyList())
        }
    }
}