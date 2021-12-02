package com.hadaralex.kepper.adapter

import com.hadaralex.kepper.model.ReadResult

interface SheetAdapter<T> {
    fun readAll(): ReadResult<T>

    // TODO this is for next version
    fun writeAll(headers: List<String>, items: T)
}