package com.khit.commons.model

import com.khit.commons.utils.KhitCellType

sealed interface KhitCell {
    val type: KhitCellType

    fun matches(other: KhitCellType): Boolean {
        return this.type == other
    }

    data class TypeBoolean(val booleanValue: Boolean) : KhitCell {
        override val type: KhitCellType get() = CELL_TYPE

        companion object {
            @JvmStatic
            val CELL_TYPE: KhitCellType = KhitCellType("Boolean")
        }
    }

    data class TypeString(val stringValue: String) : KhitCell {
        override val type: KhitCellType get() = CELL_TYPE

        companion object {
            @JvmStatic
            val CELL_TYPE: KhitCellType = KhitCellType("String")
        }
    }

    data class TypeDouble(val doubleValue: Double) : KhitCell {
        override val type: KhitCellType get() = CELL_TYPE

        companion object {
            @JvmStatic
            val CELL_TYPE: KhitCellType = KhitCellType("Boolean")
        }
    }

    /**
     * The cell is empty and the type of the content can't be defined.
     */
    object Empty : KhitCell {
        override val type: KhitCellType get() = CELL_TYPE

        @JvmStatic
        val CELL_TYPE: KhitCellType = KhitCellType("Boolean")
    }

    /**
     * The type is currently unsupported
     */
    object Unsupported : KhitCell {
        override val type: KhitCellType get() = CELL_TYPE

        @JvmStatic
        val CELL_TYPE: KhitCellType = KhitCellType("Unsupported")
    }
}