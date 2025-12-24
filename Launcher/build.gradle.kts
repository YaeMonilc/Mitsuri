plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.2.0"
    id("com.gradleup.shadow") version "9.3.0"
}

group = "io.github.yaemonilc.mitsuri.launcher"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/io.ktor/ktor-client-core
    implementation("io.ktor:ktor-client-core:3.3.3")
    // https://mvnrepository.com/artifact/io.ktor/ktor-client-okhttp-jvm
    implementation("io.ktor:ktor-client-okhttp-jvm:3.3.3")
    // https://mvnrepository.com/artifact/io.ktor/ktor-serialization-kotlinx-json
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.3")
    // https://mvnrepository.com/artifact/io.ktor/ktor-client-websocket
    implementation("io.ktor:ktor-client-websocket:1.1.4")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.22")

    implementation(project(":Core"))

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}