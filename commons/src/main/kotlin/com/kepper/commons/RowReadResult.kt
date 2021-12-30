package com.kepper.commons

import com.kepper.commons.exceptions.KepperException

/**
 *
 */
sealed class RowReadResult<out T> {

    class Success<T>(val data: T) : RowReadResult<T>()

    class Failure(val reason: KepperException) : RowReadResult<Nothing>()
}