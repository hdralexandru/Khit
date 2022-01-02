package com.khit.processor.util

object NamingUtils {
    fun buildAdapterName(className: String): String {
        return "PageAdapter_$className"
    }
}