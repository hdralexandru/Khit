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
    api(project(":utils"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}