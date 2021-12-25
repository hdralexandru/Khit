import main.kotlin.Libs

plugins {
    `java-platform`
    `maven-publish`
}

private object Versions {
    const val COROUTINES = "1.6.0"
}

dependencies {
    constraints {
        api("${Libs.Kotlin.COROUTINES}:${Versions.COROUTINES}")
    }
}