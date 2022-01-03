package com.khit.utils

object NamingUtils {
    fun buildAdapterName(className: String): String {
        return "PageAdapter_$className"
    }
}