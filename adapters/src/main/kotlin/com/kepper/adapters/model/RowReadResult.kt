package com.kepper.adapters.model

import com.kepper.commons.exceptions.KepperException

/**
 *
 */
sealed class RowReadResult<out T> {

    class Success<T>(val data: T) : RowReadResult<T>()

    class Failure(val reason: KepperException) : RowReadResult<Nothing>()

    internal companion object {

        internal inline operator fun <T> invoke(func: () -> T): RowReadResult<T> = try {
            Success(func())
        } catch (ke: KepperException) {
            Failure(ke)
        }
    }
}