import main.kotlin.Libs

plugins {
    `java-platform`
    `maven-publish`
}

private object DepsVersions {
    const val COROUTINES = "1.6.0"
    const val APACHE_POI = "5.0.0"
    const val KOTLIN_POET = "1.10.2"
}

dependencies {
    constraints {
        api("${Libs.Kotlin.COROUTINES}:${DepsVersions.COROUTINES}")
        api("${Libs.ApachePoi.Base}:${DepsVersions.APACHE_POI}")
        api("${Libs.ApachePoi.Ooxml}:${DepsVersions.APACHE_POI}")
        api("${Libs.Square.KotlinPoet}:${DepsVersions.KOTLIN_POET}")
        api("${Libs.Square.KotlinPoetKsp}:${DepsVersions.KOTLIN_POET}")
    }
}