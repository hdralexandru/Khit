package com.hadaralex.kepper.annotations

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class TableColumn(
    val columnName: String,
)