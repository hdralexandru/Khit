package com.hadaralex.khit

import com.hadaralex.khit.adapter.AdapterCache
import com.khit.commons.KhitAdapter

object Khit {
    private val cache: AdapterCache = AdapterCache()

    fun <T> adapter(type: Class<T>): KhitAdapter<T> {
        return cache[type]
    }
}