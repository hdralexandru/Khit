package com.kepper.commons.model

sealed interface CellType {
    class TypeBoolean(val booleanValue: Boolean): CellType

    class TypeString(val stringValue: String): CellType

    class TypeInt(val intValue: Int): CellType
}