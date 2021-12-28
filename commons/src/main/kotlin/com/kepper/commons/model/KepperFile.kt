package com.kepper.commons.model

interface KepperFile {
    val pagesCount: Int

    fun pages(): List<KepperPage>
}