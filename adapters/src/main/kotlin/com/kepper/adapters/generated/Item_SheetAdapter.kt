@file:Suppress("ClassName")

package com.kepper.adapters.generated

import com.kepper.adapters.KepperAdapter
import com.kepper.adapters.model.RowReadResult
import com.kepper.adapters.utils.readBooleanOr
import com.kepper.adapters.utils.readIntOr
import com.kepper.adapters.utils.readStringOr
import com.kepper.commons.exceptions.KepperException
import com.kepper.commons.model.KepperCell
import com.kepper.commons.model.KepperPage

/**
 * NOTE: We simulate how a generated class should look like.
 *
 * This way it will be easier to generate it via code.
 */
class Item_SheetAdapter : KepperAdapter<Item> {

    override suspend fun readSheet(sheet: KepperPage): List<RowReadResult<Item>> {
        // How to handle cases when it's missing index?
        // Think of default values ?
        // TODO think about default values
//        val headers : Map<String, String> = emptyMap()
//        val idIndex: Int = headers.indexOf("id")
//        val nameIndex: Int = headers.indexOf("name")
//        val availableIndex: Int = headers.indexOf("available")
//
//        val items: MutableList<RowReadResult<Item>> = mutableListOf()
//
//        for ((rowIndex: Int, row: List<CellType>) in rows.withIndex()) {
//            val next = readNextRow(row, rowIndex, idIndex, nameIndex, availableIndex)
//
//            items.add(next)
//        }

        return emptyList()
    }

    private fun readNextRow(
        row: List<KepperCell>,
        rowIndex: Int,
        idIndex: Int,
        nameIndex: Int,
        availableIndex: Int,
    ): RowReadResult<Item> {
        return try {
            val item = tryReadNextRowOrThrow(row, rowIndex, idIndex, nameIndex, availableIndex)
            RowReadResult.Success(item)
        } catch (kepperException: KepperException) {
            RowReadResult.Failure(kepperException)
        }
    }

    private fun tryReadNextRowOrThrow(
        row: List<KepperCell>,
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