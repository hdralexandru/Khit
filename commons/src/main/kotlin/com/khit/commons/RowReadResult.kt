package com.khit.commons

import com.khit.commons.exceptions.KhitException

sealed class RowReadResult<out T> {

    class Success<T>(val data: T) : RowReadResult<T>()

    class Failure(val reason: KhitException) : RowReadResult<Nothing>()

    companion object {
        inline fun <T> wrapInRowReadResult(func: () -> T): RowReadResult<T> = try {
            RowReadResult.Success(func())
        } catch (ke: KhitException) {
            RowReadResult.Failure(ke)
        }
    }
}