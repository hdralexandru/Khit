package com.kepper.commons.exceptions

sealed class KepperException(message: String?) : Exception(message)

class HeaderNotFoundException constructor(message: String? = null) : KepperException(message)

class WrongCellTypeException constructor(message: String? = null) : KepperException(message)

class AdapterNotFoundException constructor(thr: Throwable) : KepperException(thr.message)