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
    api(project(":commons"))
    api(project(":sheets"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}