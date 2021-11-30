package com.hadaralex.kepper.util

/**
 * Returns the size of the iterator, by iterating over the items inside
 * and counting them.
 *
 * This method might be expensive, since we count over iterator and we constantly request `next()`.
 * The cost depends on the implementation of the iterator under the hood.
 */
internal val Iterator<*>.size: Int
    get() {
        var size: Int = 0
        while (hasNext()) {
            size++
            next()
        }
        return size
    }