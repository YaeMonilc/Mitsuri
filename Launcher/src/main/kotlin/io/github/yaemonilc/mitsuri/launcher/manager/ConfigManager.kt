package io.github.yaemonilc.mitsuri.launcher.manager

import io.github.yaemonilc.mitsuri.launcher.entity.Config
import kotlinx.serialization.json.Json
import java.io.File

internal object ConfigManager {
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        prettyPrint = true
    }

    internal lateinit var config: Config
        private set

    internal fun init() {
        config = json.run {
            File("config.json").run {
                if (!exists()) {
                    createNewFile()

                    Config().also {
                        writeText(
                            text = encodeToString(
                                value = it
                            )
                        )
                    }
                } else {
                    decodeFromString<Config>(
                        string = readText()
                    )
                }
            }
        }
    }
}