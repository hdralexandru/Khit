package com.khit.processor.adapter

import com.kepper.commons.KepperAdapter
import java.lang.reflect.Type

internal object AdapterUtils {
    private val adapterCache: AdapterCache = AdapterCache()

    fun generateAdapterName(type: Type): String {
        return type::class.java.name
            .split(".")
            .last()
            .replace("$", "_")
    }

    operator fun get(type: Type): KepperAdapter<*> = synchronized(this) {
        return@synchronized adapterCache[type] ?: kotlin.run {
            val adapter = create(type)
            adapterCache[type] = adapter
            adapter
        }
    }

    private fun create(type: Type): KepperAdapter<*> {
        val name = generateAdapterName(type)
        val classLoader = type.javaClass.classLoader

        TODO()
    }
}