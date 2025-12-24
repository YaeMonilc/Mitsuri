package io.github.yaemonilc.mitsuri.launcher.entity

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val plugin: Plugin = Plugin(
        path = "plugins/",
        privateDirectory = "data/"
    ),
    val bots: List<Bot> = listOf()
) {
    @Serializable
    data class Plugin(
        val path: String,
        val privateDirectory: String
    )

    @Serializable
    data class Bot(
        val id: String,
        val url: String
    )
}