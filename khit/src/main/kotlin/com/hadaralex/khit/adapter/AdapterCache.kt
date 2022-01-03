package com.hadaralex.khit.adapter

import com.khit.commons.KhitAdapter
import com.khit.commons.exceptions.AdapterNotFoundException
import com.khit.utils.NamingUtils
import java.lang.reflect.Type

internal class AdapterCache {
    private val cache: MutableMap<String, KhitAdapter<*>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(type: Class<T>): KhitAdapter<T> = synchronized(this) {
        val key: String = createKey(type)
        val adapter: KhitAdapter<T> = cache[key] as? KhitAdapter<T> ?: create(type).also {
            cache[key] = it
        } as KhitAdapter<T>

        return@synchronized adapter
    }

    private fun createKey(type: Type): String = type::class.qualifiedName ?: type::class.java.simpleName

    private fun create(type: Class<*>): KhitAdapter<*> {
        val name = NamingUtils.buildAdapterName(type.simpleName)
        val pkg = type.`package`.name

        val completeName = if (pkg.isEmpty()) name else "${pkg}.${name}"
        return try {
            Class.forName(completeName)?.getDeclaredConstructor()?.newInstance() as KhitAdapter<*>
        } catch (e: Throwable) {
            throw AdapterNotFoundException(e)
        }
    }
}