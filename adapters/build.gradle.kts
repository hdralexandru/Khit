import main.kotlin.Libs

plugins {
    kotlin("jvm") version "1.5.31"
    java
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(project(":depconstraints")))
    implementation(project(":commons"))

    implementation(Libs.Kotlin.COROUTINES)
}

//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}