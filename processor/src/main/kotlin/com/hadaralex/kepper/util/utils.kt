package com.hadaralex.kepper.util

internal inline fun Any.logMe(
    extraTag: String = "",
    crossinline text: () -> String,
) {
    val tag = if (extraTag.isNotBlank()) {
        "[${this::class.java.simpleName}/$extraTag]"
    } else "[${this::class.java.simpleName}]"

    println("$tag ${text()}")
}