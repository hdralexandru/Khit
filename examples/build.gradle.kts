plugins {
    kotlin("jvm") version "1.5.31"
    java
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
     implementation(project(":kepper"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}