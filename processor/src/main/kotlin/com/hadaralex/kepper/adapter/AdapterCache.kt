package com.hadaralex.kepper.adapter

import java.lang.reflect.Type

internal class AdapterCache {
    private val cache: MutableMap<String, SheetAdapter<*>> = mutableMapOf()

    operator fun get(type: Type): SheetAdapter<*>? {
        val key = createKey(type)
        return cache[key]
    }

    operator fun set(type: Type, sheetAdapter: SheetAdapter<*>) {
        val key = createKey(type)
        cache[key] = sheetAdapter
    }

    private fun createKey(type: Type): String {
        return Type::class.java.name
    }
}