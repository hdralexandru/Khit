package com.kepper.commons.model

interface KepperRow {
    /**
     * Returns the content of the cell at the given index.
     *
     * See [KepperCell] for all the possible types.
     */
    fun getContentAt(index: Int): KepperCell
}