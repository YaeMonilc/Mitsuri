plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.2.0"
    id("com.gradleup.shadow") version "9.3.0"
}

group = "io.github.yaemonilc.mitsuri.core"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    api("ch.qos.logback:logback-classic:1.5.22")


    api(project(":OneBot"))

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}