package com.khit.commons.exceptions

sealed class KhitException(message: String?) : Exception(message)

class HeaderNotFoundException constructor(message: String? = null) : KhitException(message)

class WrongCellTypeException constructor(message: String? = null) : KhitException(message)

class AdapterNotFoundException constructor(thr: Throwable) : KhitException(thr.message)

class UnsupportedKhitException constructor(message: String?): KhitException(message)