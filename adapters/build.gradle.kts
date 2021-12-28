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
    implementation(project(":annotations"))

    implementation(Libs.Kotlin.COROUTINES)
    implementation(Libs.ApachePoi.Base)
    implementation(Libs.ApachePoi.Ooxml)
}

//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}