package com.hadaralex.khit.adapter

import com.kepper.commons.KepperAdapter
import com.kepper.commons.exceptions.AdapterNotFoundException
import com.khit.utils.NamingUtils
import java.lang.reflect.Type

internal class AdapterCache {
    private val cache: MutableMap<String, KepperAdapter<*>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(type: Class<T>): KepperAdapter<T> = synchronized(this) {
        val key: String = createKey(type)
        val adapter: KepperAdapter<T> = cache[key] as? KepperAdapter<T> ?: create(type).also {
            cache[key] = it
        } as KepperAdapter<T>

        return@synchronized adapter
    }

    private fun createKey(type: Type): String = type::class.qualifiedName ?: type::class.java.simpleName

    private fun create(type: Class<*>): KepperAdapter<*> {
        val name = NamingUtils.buildAdapterName(type.simpleName)
        val pkg = type.`package`.name

        val completeName = if (pkg.isEmpty()) name else "${pkg}.${name}"
        return try {
            Class.forName(completeName)?.getDeclaredConstructor()?.newInstance() as KepperAdapter<*>
        } catch (e: Throwable) {
            throw AdapterNotFoundException(e)
        }
    }
}