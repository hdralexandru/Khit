package com.hadaralex.kepper

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class TableColumn(
    val columnName: String,
)