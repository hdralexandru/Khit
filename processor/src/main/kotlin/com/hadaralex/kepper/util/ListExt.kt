package com.hadaralex.kepper.util

import com.hadaralex.kepper.model.CellContent

internal inline fun CellContent?.readIntOr(crossinline backupValue: () -> Int): Int {
    return if (this is CellContent.TypeInt) {
        this.intValue
    } else backupValue()
}

internal inline fun CellContent?.readBooleanOr(crossinline backupValue: () -> Boolean): Boolean {
    return if (this is CellContent.TypeBoolean) {
        this.booleanValue
    } else backupValue()
}

internal inline fun CellContent?.readStringOr(crossinline backupValue: () -> String): String {
    return if (this is CellContent.TypeString) {
        this.stringValue
    } else backupValue()
}