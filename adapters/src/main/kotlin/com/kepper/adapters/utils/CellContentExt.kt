package com.kepper.adapters.utils

import com.kepper.commons.model.CellType

internal inline fun CellType?.readIntOr(crossinline backupValue: () -> Int): Int {
    return if (this is CellType.TypeInt) {
        this.intValue
    } else backupValue()
}

internal inline fun CellType?.readBooleanOr(crossinline backupValue: () -> Boolean): Boolean {
    return if (this is CellType.TypeBoolean) {
        this.booleanValue
    } else backupValue()
}

internal inline fun CellType?.readStringOr(crossinline backupValue: () -> String): String {
    return if (this is CellType.TypeString) {
        this.stringValue
    } else backupValue()
}