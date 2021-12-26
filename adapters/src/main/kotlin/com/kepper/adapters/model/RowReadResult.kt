package com.kepper.adapters.model

import com.kepper.commons.exceptions.KepperException

/**
 *
 */
sealed interface RowReadResult<out T> {

    class Success<T>(val data: T) : RowReadResult<T>

    class Failure(val reason: KepperException) : RowReadResult<Nothing>
}