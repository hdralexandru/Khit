package com.khit.processor.adapter

import com.kepper.commons.KepperAdapter
import java.lang.reflect.Type

internal class AdapterCache {
    private val cache: MutableMap<String, KepperAdapter<*>> = mutableMapOf()

    operator fun get(type: Type): KepperAdapter<*>? {
        val key = createKey(type)
        return cache[key]
    }

    operator fun set(type: Type, sheetAdapter: KepperAdapter<*>) {
        val key = createKey(type)
        cache[key] = sheetAdapter
    }

    private fun createKey(type: Type): String {
        return Type::class.java.name
    }
}