import main.kotlin.Libs

plugins {
    kotlin("jvm") version  main.kotlin.Versions.KOTLIN
    id("com.google.devtools.ksp") version main.kotlin.Versions.KSP
    java
}

version = "unspecified"

repositories {
    mavenCentral()
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

dependencies {
    implementation(platform(project(":depconstraints")))
    implementation(project(":khit"))
    implementation(Libs.Kotlin.COROUTINES)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}