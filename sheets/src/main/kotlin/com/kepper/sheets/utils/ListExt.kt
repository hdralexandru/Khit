package com.kepper.sheets.utils

internal inline fun <T> buildList(crossinline func: MutableList<T>.() -> Unit): List<T> {
    return mutableListOf<T>().apply(func).toList()
}