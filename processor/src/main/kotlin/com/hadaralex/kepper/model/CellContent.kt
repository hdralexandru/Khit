package com.hadaralex.kepper.model

import com.hadaralex.kepper.exceptions.KepperException

// Note: atm, we only support 3 types, but we need more in the future.
sealed interface CellContent {

    class TypeInt(val intValue: Int): CellContent

    class TypeString(val stringValue: String): CellContent

    class TypeBoolean(val booleanValue: Boolean): CellContent

    class TypeError(val error: KepperException): CellContent
}