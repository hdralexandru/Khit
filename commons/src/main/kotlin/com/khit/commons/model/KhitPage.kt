package com.khit.commons.model

interface KhitPage {
    val sheetName: String

    val header: PageHeader

    val dataRowIterator: Iterator<KhitRow>
}