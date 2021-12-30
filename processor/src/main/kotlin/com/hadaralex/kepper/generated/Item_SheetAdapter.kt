@file:Suppress("ClassName")

package com.hadaralex.kepper.generated

import com.hadaralex.kepper.util.RowReader
import com.hadaralex.kepper.util.indexOfOrThrow
import com.kepper.commons.KepperAdapter
import com.kepper.commons.RowReadResult
import com.kepper.commons.exceptions.KepperException
import com.kepper.commons.model.KepperPage
import com.kepper.commons.model.PageHeader

/**
 * NOTE: We simulate how a generated class should look like.
 *
 * This way it will be easier to generate it via code.
 */
class Item_SheetAdapter : KepperAdapter<Item> {

    override suspend fun readSheet(sheet: KepperPage): List<RowReadResult<Item>> {
        // How to handle cases when it's missing index?
        // Think of default values ?
        val header: PageHeader = sheet.header
        // This part will be generated.

        // If the header is missing, throw an exception.
        // We will generate such a row for each
        val idIndex: Int = header.indexOfOrThrow("id", sheet.sheetName)
        val nameIndex: Int = header.indexOfOrThrow("name", sheet.sheetName)
        val availableIndex: Int = header.indexOfOrThrow("available", sheet.sheetName)

        val items: MutableList<RowReadResult<Item>> = mutableListOf()

        // we append underscores so it won't
        for (row in sheet.dataRowIterator) {

            val rowReadResult: RowReadResult<Item> = wrapInRowReadResult {
                // If we have default value, we will replace it. Otherwise, we do nothing and it will throw
                // We simulate that id has no default value.
                val id: Double = RowReader.readDoubleOrThrow(row, idIndex)
                val name: String = RowReader.readStringOrThrow(row, nameIndex, "__default_name_value__")
                val available: Boolean = RowReader.readBooleanOrThrow(row, availableIndex, true)

                Item(id = id, name = name, available = available)
            }

            items.add(rowReadResult)
        }

        return items
    }

    private companion object {
        inline fun <T> wrapInRowReadResult(func: () -> T): RowReadResult<T> = try {
            RowReadResult.Success(func())
        } catch (ke: KepperException) {
            RowReadResult.Failure(ke)
        }
    }

}