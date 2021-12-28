package com.kepper.commons.model

sealed interface KepperCell {
    data class TypeBoolean(val booleanValue: Boolean) : KepperCell

    data class TypeString(val stringValue: String) : KepperCell

    data class TypeDouble(val doubleValue: Double) : KepperCell

    /**
     * The cell is empty and the type of the content can't be defined.
     */
    object Empty : KepperCell

    /**
     * The type is currently unsupported
     */
    object Unsupported: KepperCell
}