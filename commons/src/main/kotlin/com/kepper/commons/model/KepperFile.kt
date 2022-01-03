package com.kepper.commons.model

import java.io.Closeable

interface KepperFile: Closeable {
    val pagesCount: Int

    fun pages(): List<KepperPage>
}