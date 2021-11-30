package com.hadaralex.kepper.exceptions

sealed class KepperException(message: String?) : Exception(message) {
    companion object {

        fun <T> throwMissingValue(index: Int, row: Int): T {
            throw MissingValueException("Missing value at index: $index, row: $row")
        }

        fun <T> throwMissingIndex(header: String): T {
            throw MissingIndexException("Missing index for header: $header. Please check your table.")
        }
    }
}

class SheetNotFoundException internal constructor(
    message: String? = null,
): KepperException(message)

class MissingValueException internal constructor(
    message: String? = null,
): KepperException(message)

class MissingIndexException internal constructor(
    message: String? = null,
): KepperException(message)