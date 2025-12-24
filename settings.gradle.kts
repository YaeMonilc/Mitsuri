plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Mitsuri"
include("OneBot")
include("Core")
include("Launcher")