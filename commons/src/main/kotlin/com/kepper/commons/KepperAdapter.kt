package com.kepper.commons

import com.kepper.commons.model.KepperPage

interface KepperAdapter<T> {
    /**
     * Processes the [sheet] and returns a list of [RowReadResult] of type [T]
     */
    suspend fun readSheet(sheet: KepperPage): List<RowReadResult<T>>
}