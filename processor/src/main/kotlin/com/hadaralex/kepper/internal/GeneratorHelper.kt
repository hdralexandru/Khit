package com.hadaralex.kepper.internal

internal object GeneratorHelper {
    fun generateAdapterName(rawType: Class<*>): String {
        return rawType.name
            .split(".")
            .last()
            .replace("$", "_")
    }
}