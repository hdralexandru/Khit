package com.kepper.sheets.utils

import com.kepper.commons.model.KepperCell

internal inline fun KepperCell?.readIntOr(crossinline backupValue: () -> Int): Int {
    return if (this is KepperCell.TypeInt) {
        this.intValue
    } else backupValue()
}

internal inline fun KepperCell?.readBooleanOr(crossinline backupValue: () -> Boolean): Boolean {
    return if (this is KepperCell.TypeBoolean) {
        this.booleanValue
    } else backupValue()
}

internal inline fun KepperCell?.readStringOr(crossinline backupValue: () -> String): String {
    return if (this is KepperCell.TypeString) {
        this.stringValue
    } else backupValue()
}