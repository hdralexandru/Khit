package com.khit.commons.model

import com.khit.commons.NO_HEADER

typealias TableHeaderCell = String

interface PageHeader {
    val size: Int

    /**
     * Returns the index of the [header] or [NO_HEADER] if it is not found.
     * Starts at 0.
     */
    fun indexOf(header: String): Int
}