package com.kepper.commons.model

import com.kepper.commons.utils.KepperCellType

sealed interface KepperCell {
    val type: KepperCellType

    fun matches(other: KepperCellType): Boolean {
        return this.type == other
    }

    data class TypeBoolean(val booleanValue: Boolean) : KepperCell {
        override val type: KepperCellType get() = CELL_TYPE

        companion object {
            @JvmStatic
            val CELL_TYPE: KepperCellType = KepperCellType("Boolean")
        }
    }

    data class TypeString(val stringValue: String) : KepperCell {
        override val type: KepperCellType get() = CELL_TYPE

        companion object {
            @JvmStatic
            val CELL_TYPE: KepperCellType = KepperCellType("String")
        }
    }

    data class TypeDouble(val doubleValue: Double) : KepperCell {
        override val type: KepperCellType get() = CELL_TYPE

        companion object {
            @JvmStatic
            val CELL_TYPE: KepperCellType = KepperCellType("Boolean")
        }
    }

    /**
     * The cell is empty and the type of the content can't be defined.
     */
    object Empty : KepperCell {
        override val type: KepperCellType get() = CELL_TYPE

        @JvmStatic
        val CELL_TYPE: KepperCellType = KepperCellType("Boolean")
    }

    /**
     * The type is currently unsupported
     */
    object Unsupported : KepperCell {
        override val type: KepperCellType get() = CELL_TYPE

        @JvmStatic
        val CELL_TYPE: KepperCellType = KepperCellType("Unsupported")
    }
}