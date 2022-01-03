package com.khit.commons.model

import java.io.Closeable

interface KhitFile: Closeable {
    val pagesCount: Int

    fun pages(): List<KhitPage>
}