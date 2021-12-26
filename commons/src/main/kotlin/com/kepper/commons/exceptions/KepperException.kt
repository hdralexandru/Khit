package com.kepper.commons.exceptions

import java.lang.reflect.Type

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
) : KepperException(message)

class MissingValueException internal constructor(
    message: String? = null,
) : KepperException(message)

class MissingIndexException internal constructor(
    message: String? = null,
) : KepperException(message)

class AdapterNotFoundException internal constructor(
    message: String? = null,
) : KepperException(message) {
    internal constructor(type: Type) : this(
        "Couldn\'t find and adapter for class ${type::class.java}. Make sure it's properly annotated"
    )
}