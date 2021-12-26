package com.kepper.commons.model

interface KepperFile {
    val name: String

    val pages: Iterator<KepperPage>
}