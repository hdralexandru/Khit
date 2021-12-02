package com.hadaralex.kepper.model

internal interface SheetHeaders {
    val size: Int

    @Throws(IndexOutOfBoundsException::class)
    fun itemAtPosition(position: Int)
}