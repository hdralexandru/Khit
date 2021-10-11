package com.hadaralex.kepper

@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
annotation class XcelSheet(
    val sheetName: String,
)