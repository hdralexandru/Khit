import main.kotlin.Libs

plugins {
    kotlin("jvm") version main.kotlin.Versions.KOTLIN
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(project(":depconstraints")))
    implementation(project(":commons"))
}