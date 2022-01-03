package com.hadaralex.khit.annotations

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class Key(
    val name: String,
)