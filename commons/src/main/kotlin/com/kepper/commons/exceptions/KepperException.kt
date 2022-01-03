package com.kepper.commons.exceptions

import java.lang.reflect.Type

sealed class KepperException(message: String?) : Exception(message)

class HeaderNotFoundException constructor(message: String? = null) : KepperException(message)

class WrongCellTypeException constructor(message: String? = null): KepperException(message)

class AdapterNotFoundException constructor(
    message: String? = null,
) : KepperException(message) {
    internal constructor(type: Type) : this(
        "Couldn\'t find and adapter for class ${type::class.java}. Make sure it's properly annotated"
    )
}