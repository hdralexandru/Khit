package com.kepper.commons.model

interface KepperFile {
    val pagesCount: Int

    fun pageList(): List<KepperPage>
}