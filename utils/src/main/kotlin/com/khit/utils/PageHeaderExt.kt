package com.khit.utils

import com.kepper.commons.NO_HEADER
import com.kepper.commons.exceptions.HeaderNotFoundException
import com.kepper.commons.model.PageHeader

/**
 * Returns the index of [header] or throws
 */
fun PageHeader.indexOfOrThrow(header: String, sheetName: String, shouldThrow: Boolean = true): Int {
    val index = this.indexOf(header)
    if (index == NO_HEADER && shouldThrow) {
        throw HeaderNotFoundException("The header with $header was not found in the sheet named $sheetName!")
    }

    return index
}