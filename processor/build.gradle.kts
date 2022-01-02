import main.kotlin.Libs

plugins {
    kotlin("jvm") version main.kotlin.Versions.KOTLIN
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":commons"))
    implementation(platform(project(":depconstraints")))
    implementation("com.google.devtools.ksp:symbol-processing-api:1.5.31-1.0.0")

    implementation(Libs.ApachePoi.Base)
    implementation(Libs.ApachePoi.Ooxml)
    implementation(Libs.Square.KotlinPoet)
    implementation(Libs.Square.KotlinPoetKsp)
}