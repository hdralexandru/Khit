package com.kepper.commons.model

interface KepperPage {
    val sheetName: String

    val header: PageHeader

    val dataRowIterator: Iterator<KepperCell>
}