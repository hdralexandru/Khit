import main.kotlin.Libs

plugins {
    kotlin("jvm") version main.kotlin.Versions.KOTLIN
    java
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(project(":depconstraints")))
    implementation(project(":commons"))
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation(Libs.Kotlin.COROUTINES)
    implementation(Libs.ApachePoi.Base)
    implementation(Libs.ApachePoi.Ooxml)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}