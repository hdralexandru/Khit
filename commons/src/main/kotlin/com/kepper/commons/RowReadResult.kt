package com.kepper.commons

import com.kepper.commons.exceptions.KepperException

sealed class RowReadResult<out T> {

    class Success<T>(val data: T) : RowReadResult<T>()

    class Failure(val reason: KepperException) : RowReadResult<Nothing>()

    companion object {
        inline fun <T> wrapInRowReadResult(func: () -> T): RowReadResult<T> = try {
            RowReadResult.Success(func())
        } catch (ke: KepperException) {
            RowReadResult.Failure(ke)
        }
    }
}