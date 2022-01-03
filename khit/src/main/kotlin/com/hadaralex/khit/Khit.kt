package com.hadaralex.khit

import com.hadaralex.khit.adapter.AdapterCache
import com.kepper.commons.KepperAdapter

object Khit {
    private val cache: AdapterCache = AdapterCache()

    fun <T> adapter(type: Class<T>): KepperAdapter<T> {
        return cache[type]
    }
}