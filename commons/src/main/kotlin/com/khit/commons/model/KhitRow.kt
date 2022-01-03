package com.khit.commons.model

interface KhitRow {
    /**
     * Returns the content of the cell at the given index.
     *
     * See [KhitCell] for all the possible types.
     */
    fun getContentAt(index: Int): KhitCell
}