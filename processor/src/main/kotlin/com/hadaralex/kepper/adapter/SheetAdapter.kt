package com.hadaralex.kepper.adapter

import java.lang.reflect.Type

interface SheetAdapter<T> {
    fun readAll(): ReadResult<T>

    // TODO this is for next version
//    fun writeAll(headers: List<String>, items: T)


    interface Factory {
        fun create(type: Type): SheetAdapter<*>
    }
}