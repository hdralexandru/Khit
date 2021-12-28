package com.kepper.commons.model

sealed interface KepperCell {
    class TypeBoolean(val booleanValue: Boolean): KepperCell

    class TypeString(val stringValue: String): KepperCell

    class TypeInt(val intValue: Int): KepperCell
}