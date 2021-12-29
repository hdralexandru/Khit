import main.kotlin.Libs

plugins {
    kotlin("jvm") version "1.5.30"
    id("com.google.devtools.ksp") version "1.5.30-1.0.0-beta09"
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
    implementation(project(":annotations"))
    implementation(project(":sheets"))
    implementation(project(":adapters"))

    implementation(Libs.Kotlin.COROUTINES)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}