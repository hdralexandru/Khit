package com.kepper.sheets.utils

import com.kepper.commons.model.KepperCell

internal inline fun KepperCell?.readDoubleOr(crossinline backupValue: () -> Double): Double {
    return if (this is KepperCell.TypeDouble) {
        this.doubleValue
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