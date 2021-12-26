@file:Suppress("ClassName")

package com.hadaralex.kepper.generated

import com.hadaralex.kepper.adapter.SheetAdapter
import com.hadaralex.kepper.exceptions.KepperException
import com.hadaralex.kepper.internal.SheetHeaders
import com.hadaralex.kepper.tryout.Item
import com.hadaralex.kepper.util.readBooleanOr
import com.hadaralex.kepper.util.readIntOr
import com.hadaralex.kepper.util.readStringOr

/**
 * NOTE: We simulate how a generated class should look like.
 *
 * This way it will be easier to generate it via code.
 */
class Item_SheetAdapter: SheetAdapter<Item> {
    override fun readAll(): ReadResult<Item> {
        TODO("Not yet implemented")
    }

    fun toListOfItems(headers: SheetHeaders, rows: List<List<CellContent>>): List<ReadResult<Item>> {

        // How to handle cases when it's missing index?
        // Think of default values ?
        // TODO think about default values
        val idIndex: Int = headers.indexOf("id")
        val nameIndex: Int = headers.indexOf("name")
        val availableIndex: Int = headers.indexOf("available")

        val items: MutableList<ReadResult<Item>> = mutableListOf()

        for ((rowIndex: Int, row: List<CellContent>) in rows.withIndex()) {
            val next = readNextRow(row, rowIndex, idIndex, nameIndex, availableIndex)

            items.add(next)
        }

        return items.toList()
    }

    private fun readNextRow(
        row: List<CellContent>,
        rowIndex: Int,
        idIndex: Int,
        nameIndex: Int,
        availableIndex: Int,
    ): ReadResult<Item> {
        return try {
            val item = tryReadNextRowOrThrow(row, rowIndex, idIndex, nameIndex, availableIndex)
            ReadResult.Success(item)
        } catch (kepperException: KepperException) {
            ReadResult.Failure(kepperException)
        }
    }

    private fun tryReadNextRowOrThrow(
        row: List<CellContent>,
        rowIndex: Int,
        idIndex: Int,
        nameIndex: Int,
        availableIndex: Int,
    ): Item {
        // Note: we'll generate a var for each element
        // The names will be the ones from the annotation
        val id: Int = row.getOrNull(idIndex).readIntOr {
            // In case we're missing the value and, we have no default value, we throw exception
            KepperException.throwMissingValue(idIndex, rowIndex)
        }
        val name: String = row.getOrNull(nameIndex).readStringOr {
            "default_string_value"
        }
        val available: Boolean = row.getOrNull(availableIndex).readBooleanOr {
            false
        }

        return Item(
            id = id,
            name = name,
            available = available
        )
    }
}