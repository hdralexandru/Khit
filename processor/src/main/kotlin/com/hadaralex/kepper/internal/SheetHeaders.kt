package com.hadaralex.kepper.internal

class SheetHeaders private constructor(
    private val headers: List<String>
) {
    /**
     * Returns the index of [header] in the row.
     * If the [header] is not present, will return
     */
    fun indexOf(header: String): Int {
        val index = headers.indexOf(header)
        return if (index >= 0) index else MISSING_HEADER
    }


    companion object {
        const val MISSING_HEADER: Int = -1
    }
}