plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.2.0"
}

group = "io.github.yaemonilc.mitsuri.onebot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/io.ktor/ktor-client-core
    compileOnly("io.ktor:ktor-client-core:3.3.3")
    // https://mvnrepository.com/artifact/io.ktor/ktor-client-okhttp-jvm
    compileOnly("io.ktor:ktor-client-okhttp-jvm:3.3.3")
    // https://mvnrepository.com/artifact/io.ktor/ktor-serialization-kotlinx-json
    compileOnly("io.ktor:ktor-serialization-kotlinx-json:3.3.3")
    // https://mvnrepository.com/artifact/io.ktor/ktor-client-websocket
    compileOnly("io.ktor:ktor-client-websocket:1.1.4")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}