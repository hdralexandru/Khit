subprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile>() {
        options.compilerArgs.add("-Xuse-experimental=com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview")
    }
}