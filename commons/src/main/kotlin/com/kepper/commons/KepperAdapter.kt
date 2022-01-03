package com.kepper.commons

import com.kepper.commons.model.KepperPage

interface KepperAdapter<T> {
    /**
     * Processes the [page] and returns a list of [RowReadResult] of type [T]
     */
    suspend fun readSheet(page: KepperPage): List<RowReadResult<T>>
}