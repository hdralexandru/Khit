package com.kepper.adapters

import com.kepper.adapters.model.RowReadResult
import com.kepper.commons.model.KepperPage

interface KepperAdapter<T> {
    /**
     * Processes the [sheet] and returns a list of
     */
    suspend fun readSheet(sheet: KepperPage): List<RowReadResult<T>>
}