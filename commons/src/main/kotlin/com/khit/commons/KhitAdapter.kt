package com.khit.commons

import com.khit.commons.model.KhitPage

interface KhitAdapter<T> {
    /**
     * Processes the [page] and returns a list of [RowReadResult] of type [T]
     */
    suspend fun readSheet(page: KhitPage): List<RowReadResult<T>>
}