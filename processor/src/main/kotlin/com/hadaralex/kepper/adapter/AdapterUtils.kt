package com.hadaralex.kepper.adapter

import java.lang.reflect.Type

internal object AdapterUtils {
    private val adapterCache: AdapterCache = AdapterCache()

    fun generateAdapterName(type: Type): String {
        return type::class.java.name
            .split(".")
            .last()
            .replace("$", "_")
    }

    operator fun get(type: Type): SheetAdapter<*> = synchronized(this) {
        return@synchronized adapterCache[type] ?: kotlin.run {
            val adapter = create(type)
            adapterCache[type] = adapter
            adapter
        }
    }

    private fun create(type: Type): SheetAdapter<*> {
        val name = generateAdapterName(type)
        val classLoader = type.javaClass.classLoader

        TODO()
    }
}