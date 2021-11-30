package com.hadaralex.kepper.model

import com.hadaralex.kepper.exceptions.KepperException

sealed interface ReadResult<out T> {

    class Success<T>(val data: T): ReadResult<T>

    class Failure(val reason: KepperException): ReadResult<Nothing>
}